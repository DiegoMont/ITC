#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdlib.h>
#include <unistd.h>

void logErrorAndExit(const char* errorMsg);
unsigned long long factorial(short n);

const char* SERVER_IP_ADDRESS = "127.0.0.1";
const int TCP_PORT = 8000;

// https://man7.org/linux/man-pages/man2/socket.2.html

int main() {
    // Create socket
    int socketServerFileDescriptor;
    socketServerFileDescriptor = socket(PF_INET, SOCK_STREAM, 0);
    if(socketServerFileDescriptor == -1)
        logErrorAndExit("Couldn't create socket");
    
    // Bind IP address to socket
    struct sockaddr_in socketAddressInfo;
    socketAddressInfo.sin_port = htons(TCP_PORT);
    socketAddressInfo.sin_family = AF_INET;
    inet_aton(SERVER_IP_ADDRESS, &socketAddressInfo.sin_addr);
    socklen_t addressInfoSize = sizeof(socketAddressInfo);
    bind(socketServerFileDescriptor, (struct sockaddr *) &socketAddressInfo, addressInfoSize);
    
    // Start listening
    int listeningStatus = listen(socketServerFileDescriptor, 10);
    if(listeningStatus == -1)
      logErrorAndExit("Can't listen in that port");
    
    // Establish connection with client
    int socketClientFileDescriptor;
    socketClientFileDescriptor = accept(socketServerFileDescriptor, (struct sockaddr *) &socketAddressInfo, &addressInfoSize);
    if(socketClientFileDescriptor == -1){
        close(socketServerFileDescriptor);
        logErrorAndExit("Couldn't accept client");
    }

    printf("Client connected to %s:%d \n",
           inet_ntoa(socketAddressInfo.sin_addr),
           ntohs(socketAddressInfo.sin_port));

    // Waiting for client to send input
    char buffer[10];
    size_t bufferSize = sizeof(buffer);
    puts("Send negative number to end program");
    int readedBytes = read(socketClientFileDescriptor, &buffer, bufferSize);
    while(readedBytes){
        puts("Request from client received");
        short n = (short) atoi(buffer);
        if(n < 0){
            puts("Ending communication");
            break;
        } else {
            unsigned long long result = factorial(n);
            char resultText[30] = {};
            sprintf(resultText, "%llu\n", result);
            size_t msgSize = sizeof(resultText);
            write(socketClientFileDescriptor, resultText, msgSize);
        }
        readedBytes = read(socketClientFileDescriptor, &buffer, bufferSize);
    }

    // Cleaning up
    puts("Closing sockets");
    close(socketServerFileDescriptor);
    close(socketClientFileDescriptor);
}

void logErrorAndExit(const char* errorMsg){
    puts(errorMsg);
    puts("Terminating program");
    exit(1);
}

unsigned long long factorial(short n){
    unsigned long long resultado;
    if(n < 2)
        resultado = 1;
    else
      resultado = factorial(n-1) * ((unsigned long long) n);
    return resultado;
}

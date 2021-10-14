#include <stdio.h>
#include <pthread.h>

const long long BIG_NUMBER = 5000000000;

long long sumaTotal;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void suma1(void* arg){
    long long sumaParcial = 0;
    long long fin = BIG_NUMBER / 2;
    for(long long i = 1; i < fin; i++)
        sumaParcial += i;
    pthread_mutex_lock(&mutex);
    sumaTotal += sumaParcial;
    pthread_mutex_unlock(&mutex);
}

void suma2(void* arg){
    long long sumaParcial = 0;
    long long fin = BIG_NUMBER + 1;
    for(long long i = BIG_NUMBER / 2; i < fin; i++)
        sumaParcial += i;
    pthread_mutex_lock(&mutex);
    sumaTotal += sumaParcial;
    pthread_mutex_unlock(&mutex);
}

int main() {
    pthread_t hilo1, hilo2;
    sumaTotal = 0;
    pthread_create(&hilo1, NULL, (void*) suma1, NULL);
    pthread_create(&hilo2, NULL, (void*) suma2, NULL);
    pthread_join(hilo1, NULL);
    pthread_join(hilo1, NULL);
    printf("La suma de 1 hasta %lld es %lld\n", BIG_NUMBER, sumaTotal);
}

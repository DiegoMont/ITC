#include <stdio.h>
#include <stdlib.h>

char* copyString(const char* cadenaOriginal);
void reverseString(const char* cadena);
int getLength(const char* cadena);

int main(){
  const char* cadena = "adios";

  printf("Address: %p\n", cadena);
  int length = getLength(cadena);
  printf("%d\n", length);

  char* copia = copyString(cadena);
  printf("Address: %p\n", copia);
  printf("%s\n", copia);

  reverseString(copia);
  printf("Address: %p\n", copia);
  printf("%s\n", copia);
}

char* copyString(const char* cadenaOriginal){
  int length = getLength(cadenaOriginal);
  char* copia = (char*) malloc(length * sizeof(char)+1);
  for(int i = 0; i < length; i++){
    *(copia + i) = *(cadenaOriginal + i);
  }
  *(copia + length) = 0;
  return copia;
}

void reverseString(char* reversedString){
  int length = getLength(reversedString);
  char* fin  = reversedString + length - 1;
  int limite = length / 2;
  for(int i = 0; i < limite; i++){
    char aux = *(reversedString + i);
    *(reversedString + i) = *(fin - i);
    *(fin - i) = aux;
  }
}

int getLength(const char* cadena){
  int count = 0;
  while(*(cadena + count) != 0)
    count++;
  return count;
}

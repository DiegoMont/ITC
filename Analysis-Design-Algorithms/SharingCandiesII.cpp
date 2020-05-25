/*
Pik and Puk are great friends. Sometimes they fight, other times they scamper, and other times they go to parties together. When this happens, they always like to share the candies equally.
To do this, they first place all the collected bags on a table and check if everyone can have the same amount of candy without opening the bags. Help them to solve this problem.
INPUT
  *The first line contains the value of N, the number of candy bags
  *The next N lines contains an integer determining the number of candies in a bag
OUTPUT
A boolean value determining whether it is possible or not to evenly distribute the candy bags
SAMPLE INPUT:
49
2 16 14 9 5 15 12 9 7 9 12 2 17 18 17 5 16 2 16 18 7 9 4 2 3 17 14 19 2 6 20 15 17 20 18 10 1 11 10 15 9 13 10 13 1 8 11 30 31
SAMPLE OUTPUT
False
*/

#include<stdio.h>

bool repartirBolsas(int* bags, int pik, int puk, int n, int i);

int main(){
  int n;
  scanf("%i", &n);
  int bags[n];
  int suma = 0;

  for(int i = 0; i < n; i++){
    scanf("%i", bags+i);
    suma += bags[i];
  }

  if (suma%2 == 1) {
    printf("False\n");
    return 0;
  }

  printf(repartirBolsas(bags, 0, 0, n, 0) ? "True\n": "False\n");
}

bool repartirBolsas(int* bags, int pik, int puk, int n, int i){
  if(i == n) {
    return pik == puk;
  } else {
    return repartirBolsas(bags, pik + bags[i], puk, n, i+1) || repartirBolsas(bags, pik, puk + bags[i], n, i+1);
  }
}

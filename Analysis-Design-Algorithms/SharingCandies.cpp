/*
Pik and Puk are great friends. Sometimes they fight, other times they scamper, and other times they go to parties together. When this happens, they always like to share the candies equally.
To do this, they first place all the collected bags on a table and check if everyone can have the same amount of candy without opening the bags. Help them to solve this problem.
INPUT
  *The first line contains the value of N, the number of candy bags
  *The next N lines contains an integer determining the number of candies in a bag
OUTPUT
A boolean value determining whether it is possible or not to evenly distribute the candy bags
SAMPLE INPUT:
17
5 4 7 10 8 4 7 3 4 4 3 1 4 9 3 10 4
SAMPLE OUTPUT
True
*/

#include<stdio.h>

bool repartirBolsas(int* bags, int pik, int puk, int n, int i);

int main(){
  int n;
  scanf("%i", &n);
  int bags[n];

  for(int i = 0; i < n; i++){
    scanf("%i", bags+i);
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

/*
A university student has a collection of T-shirts of his favorite soccer teams and he wants to order them according to two characteristics: the team name and the nationality of the team.
Your task is to help him in his work.
Inputs:
  N, the number of T-shirts
  The following 2N lines contain the team name and the nationality of every T-shirt
Outputs:
  An ordered list of T-shirts (ascendingly). First order them by the nationality and if they have the same value, order them by the team name.
SAMPLE INPUT:
4
Real Madrid
Spain
Manchester City
England
Barcelona
Spain
Wolfsburg
England
SAMPLE OUTPUT:
Manchester City
Wolfsburg
Barcelona
Real Madrid
*/
#include<iostream>
#include<stdio.h>
#include<string>

void bubbleSort(std::string teams[], std::string nationalities[], int n);
void swap(std::string teams[], std::string nationalities[], int a, int b);

int main(){
  int n;
  scanf("%i\n", &n);
  std::string teams[n], nationalities[n];
  for (int i = 0; i < n; i++) {
    std::getline(std::cin, teams[i]);
    std::getline(std::cin, nationalities[i]);
  }

  bubbleSort(nationalities, teams, n);

  for(int i = 0; i < n; i++)
    std::cout << teams[i] << "\n";
}

void bubbleSort(std::string arreglo1[], std::string arreglo2[], int n) {
  int i = 1;
  while (n > i) {
    int j = i;
    while (j > 0 && (arreglo1[j-1].compare(arreglo1[j]) > 0 || (arreglo1[j-1].compare(arreglo1[j]) == 0 && arreglo2[j-1].compare(arreglo2[j]) > 0))) {
      swap(arreglo1, arreglo2, j, j-1);
      j--;
    }
    i++;
  }
}

void swap(std::string teams[], std::string nationalities[], int a, int b) {
  std::string temp = teams[a];
  teams[a] = teams[b];
  teams[b] = temp;
  std::string temp1 = nationalities[a];
  nationalities[a] = nationalities[b];
  nationalities[b] = temp1;
}

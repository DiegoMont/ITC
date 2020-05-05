/*
Pik and Pok are great friends. They like to play games with numbers. This time, Pik has given Pok a list of numbers and given him the task of determining if it is possible to choose a subset of them such that they sum is equal to another given number.
Build an algorithm using dynamic programming to help Pok with his problem.
INPUT
The first line corresponds to N, the amount of numbers given by Pik
The next N lines contain each of the numbers
The last line contains the desired sum
OUTPUT
True or False (Whether is possible to obtain the desired sum or not)
SAMPLE INPUT: 25 88 73 90 43 86 50 30 70 56 42 60 25 79 43 24 60 77 64 72 35 46 2 52 64 38 925
SAMPLE OUTPUT: True
*/
#include<stdio.h>
#include<iostream>

bool findSum(int numeros[], int n, int sum, int i);

int main(){
  int n, result;
  scanf("%i", &n);
  int numeros[n];
  for(int i = 0; i < n; i++){
    scanf("%i", &numeros[i]);
  }
  scanf("%i", &result);
  std::cout << (findSum(numeros, n, result, 0) ? "True": "False") << "\n";
}

bool findSum(int numeros[], int n, int sum, int i){
  if (sum < 1 || i == n) {
    return sum == 0;
  } else {
    int diferencia = sum - numeros[i];
    i++;
    return findSum(numeros, n, diferencia, i) || findSum(numeros, n, sum, i);
  }
}

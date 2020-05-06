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
  if (i == n) {
    return sum == 0;
  } else {
    int diferencia = sum - numeros[i];
    i++;
    return findSum(numeros, n, diferencia, i) || findSum(numeros, n, sum, i);
  }
}

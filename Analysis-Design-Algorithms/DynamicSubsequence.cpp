/*
Build a Dynamic Programming algorithm for the following problem.
Given two sequences of vowels, find the length of longest subsequence present in both of them.
Note: A subsequence is a sequence that appears in the same order, but not necessarily contiguous. For example, "aei", "aeo", "eio", "aiu", ... are subsequences of "aeiou".
INPUT: uiaueoiouaeoeiieaiuoooaaouaeoaaiiaeoeueauioeaaooaiiuuuuei oaiuioaoeaauueouioeoeauaaueeioaioaauuueoei
OUTPUT: 29
*/
#include<iostream>
#include<stdio.h>
#include<string>
#include<map>

int recursivo(std::string sequence1, std::string sequence2, std::map<std::string, int>& tablaDinamica);
int recursivoDinamico(std::string sequence1, std::string sequence2, std::map<std::string, int>& tablaDinamica);

int main(){
  std::string sequence1;
  std::string sequence2;
  std::cin >> sequence1;
  std::cin >> sequence2;
  std::map<std::string, int> tablaDinamica;
  int resultado = recursivo(sequence1, sequence2, tablaDinamica);
  printf("%i\n", resultado);
}

int recursivoDinamico(std::string sequence1, std::string sequence2, std::map<std::string, int>& tablaDinamica){
  if(tablaDinamica.find(sequence1 + sequence2) != tablaDinamica.end()) {
    return tablaDinamica[sequence1 + sequence2];
  } else if(tablaDinamica.find(sequence2 + sequence1) != tablaDinamica.end()){
    return tablaDinamica[sequence2 + sequence1];
  } else {
    tablaDinamica[sequence1 + sequence2] = recursivo(sequence1, sequence2, tablaDinamica);
    return tablaDinamica[sequence1 + sequence2];
  }
}

int recursivo(std::string sequence1, std::string sequence2, std::map<std::string, int>& tablaDinamica){
  //std::cout << sequence1 << "   " << sequence2 << "\n";
  if (sequence1 == "" || sequence2 == "") {
    return 0;
  } else if(sequence1.at(0) == sequence2.at(0)){
    return  1 + recursivoDinamico(sequence1.substr(1), sequence2.substr(1), tablaDinamica);
  } else {
    return std::max(recursivoDinamico(sequence1.substr(1), sequence2, tablaDinamica), recursivoDinamico(sequence1, sequence2.substr(1), tablaDinamica));
  }
}

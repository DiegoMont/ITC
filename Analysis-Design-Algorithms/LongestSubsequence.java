/*
Build a brute force approach for the following problem.
Given two sequences of vowels, find the length of longest subsequence present in both of them.
Note: A subsequence is a sequence that appears in the same order, but not necessarily contiguous. For example, "aei", "aeo", "eio", "aiu", ... are subsequences of "aeiou".
INPUT: oeoaiouuee ooioao
OUTPUT: 4
*/

import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class LongestSubsequence {

  private static int resultado;
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new File("input.txt"));
    String sequence1 = sc.next();
    String sequence2 = sc.next();

    /*int[] letras = encontrarLetra(sequence1, sequence2, 0, 0);
    while(letras[0] != -1){
      System.out.print(sequence1.charAt(letras[0]));
      letras = encontrarLetra(sequence1, sequence2, letras[0]+1, letras[1]+1);
    }
    System.out.println("");*/
    recursivo(sequence1, sequence2, "");
    System.out.println(resultado);
  }

  static void recursivo(String sequence1, String sequence2, String subs){
    if(sequence1.equals("") || sequence2.equals("")){
      //System.out.println(subs);
      checarMaximo(subs);
      return;
    }
    //System.out.println(sequence1 + "   " + sequence2);
    if(sequence1.charAt(0) == sequence2.charAt(0)){
      if(sequence1.length() > 1){
        if(sequence2.length() > 1){
          recursivo(sequence1.substring(1), sequence2.substring(1), subs + sequence1.charAt(0));
        } else {
          //System.out.println(subs + sequence2);
          checarMaximo(subs + sequence2);
        }
      } else {
        //System.out.println(subs + sequence1);
        checarMaximo(subs + sequence1);
      }
    } else { // son diferentes
      if(sequence1.length() > 1){
        recursivo(sequence1.substring(1), sequence2, subs);
        if(sequence2.length() > 1){
          recursivo(sequence1, sequence2.substring(1), subs);
        }
      } else {
        if(sequence2.length() > 1){
          recursivo(sequence1, sequence2.substring(1), subs);
        } else {
          //System.out.println(subs);
          checarMaximo(subs);
        }
      }
    }
  }

  static void checarMaximo(String subs){
    if (subs.length() > resultado) {
      //System.out.println(subs);
      resultado = subs.length();
    }
  }

  static int[] encontrarLetra(String sequence1, String sequence2, int inicio1, int inicio2){
    //System.out.println(sequence1 + "   " + sequence2);
    int[] letras = {-1, -1};
    if(!(sequence1.equals("") || sequence2.equals("")))
    for (int i = inicio1; i < sequence1.length(); i++) {
      for(int j = inicio2; j < sequence2.length(); j++){
        if(sequence1.charAt(i) == sequence2.charAt(j)){
          letras[0] = i;
          letras[1] = j;
          return letras;
        }
      }
    }
    return letras;
  }
}

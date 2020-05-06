/*
Build a brute force approach for the following problem.
Given two sequences of vowels, find the length of longest subsequence present in both of them.
Note: A subsequence is a sequence that appears in the same order, but not necessarily contiguous. For example, "aei", "aeo", "eio", "aiu", ... are subsequences of "aeiou".
INPUT: oeoaiouuee ooioao
OUTPUT: 4
*/

import java.util.Scanner;

public class LongestSubsequence {

  private static int resultado;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String sequence1 = sc.next();
    String sequence2 = sc.next();

    recursivo(sequence1, sequence2, "");
    System.out.println(resultado);
  }

  static void recursivo(String sequence1, String sequence2, String subs){
    //System.out.println(sequence1 + "   " + sequence2);
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
      System.out.println(subs);
      resultado = subs.length();
    }
  }
}

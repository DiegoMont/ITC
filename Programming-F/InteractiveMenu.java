import java.util.Scanner;
class InteractiveMenu{

  public static Scanner consola = new Scanner(System.in);

  public static void imprimirMenu(int numeroMenor, int numeroMayor){
    System.out.println("\n          Menu");
    for (int i=1; i<=3; i++) {
      switch (i) {
        case 1: System.out.print("\n1. Print odd numbers "); break;
        case 2: System.out.print("2. Print even numbers "); break;
        case 3: System.out.print("3. Guess a number "); break;
      }
      System.out.println("in between " + numeroMenor + " and " + numeroMayor);
    }
    System.out.print("4. End!\n\nType the number of the option you want to choose: ");
  }

  public static boolean ejecutarSeleccion(int opcion, int numero1, int numero2){
    switch (opcion) {
      case 1: return oddEvenBetween(opcion, numero1, numero2);
      case 2: return oddEvenBetween(opcion, numero1, numero2);
      case 3: return guessingGame(numero1, numero2);
      case 4: return false;
      default: return true;
    }
  }

  public static boolean oddEvenBetween(int par, int numMenor, int numMayor){
    for(int i = numMenor; i <= numMayor; i++){
      if (par == 1&&(i-1)%2==0) {
        System.out.print(i+", ");
      }else if (par == 2 && i%2==0) {
        System.out.print(i+", ");
      }
    }
    System.out.print("\n");
    return true;
  }

  public static boolean guessingGame(int numMenor, int numMayor){
    int secreto = (int)(numMenor + Math.ceil(Math.random()*(numMayor-numMenor)));
    System.out.print("\nInsert a number in between "+numMenor+" and "+numMayor+": ");
    int guess = consola.nextInt();
    int i = 1;
    while (guess != secreto) {
      if (guess < secreto) {
        System.out.print("The secret number is higher: ");
      } else {
        System.out.print("The secret number is lower: ");
      }
      guess = consola.nextInt();
      i++;
    }
    System.out.println("\nCongratulations! You guessed the number in "+i+" tries.");
    return true;
  }

  public static void main(String[] args) {

    System.out.print("Enter a number: ");
    int num1 = consola.nextInt();
    System.out.print("Enter another number: ");
    int num2 = consola.nextInt();
    if (num1>num2) {
      int a = num1;
      num1 = num2;
      num2 = a;
    }
    do {
      imprimirMenu(num1, num2);
    } while (ejecutarSeleccion(consola.nextInt(),num1, num2));
    System.out.println("Thanks for playing!");
  }
}

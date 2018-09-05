import java.util.Scanner;
class TwentyOne{
  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    System.out.print("Write an integer number greater than 0: ");
    int num1 = 21-consola.nextInt();
    System.out.print(num1 < 21 ? "Write another integer number greater than 0: ": "The number must be greater than ZERO");
    int num2 = 21-consola.nextInt();
    if (num1 < 21 && num1 >= 0 && (num2 < 0 || num1 <= num2)) {
      System.out.println("The nearest value to 21 is " + (21-num1));
    } else if (num2 < 21 && num2 >= 0 && (num1>=num2 || num1 < 0)) {
      System.out.println("The nearest value to 21 is " + (21-num2));
    } else {
      System.out.println("0");
    }
  }
}

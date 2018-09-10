import java.util.Scanner;
class nearTen{
  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    System.out.print("Write a non negative number: ");
    double num = consola.nextDouble();
    if (num > 0) {
      double diez = Math.round(num/10)*10;
      if (diez-num<=2 && diez-num>=-2) {
        System.out.println("true");
      } else {
        System.out.println("false");
      }
    } else {
      System.out.println("false");
    }
  }
}

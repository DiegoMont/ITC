import java.util.Scanner;
class NearTen{
  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    System.out.print("Write a non negative number: ");
    double num = consola.nextDouble();
    double diez = Math.round(num/10)*10;
    if (num >= 0 && diez-num<=2 && diez-num>=-2) {
      System.out.println("true");
    } else {
      System.out.println("false");
    }
  }
}

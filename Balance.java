import java.util.Scanner;
class Balance{
  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    System.out.print("Previous month balance: ");
    double mes = consola.nextDouble();
    System.out.print("Issued checks: ");
    int cheques = consola.nextInt();
    System.out.print("Income: ");
    double ing = consola.nextDouble();
    System.out.print("Expenses: ");
    double egr = consola.nextDouble();
    System.out.print("What type of account do you have? ");
    System.out.println("\nMonthly interest: "+((mes -egr + ing -(cheques-5>5?(cheques-5)*3:0)))*0.075);
    System.out.println("Total monthly balance: ");
  }
}

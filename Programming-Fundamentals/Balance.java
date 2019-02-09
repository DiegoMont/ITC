import java.util.Scanner;
class Balance{
  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    System.out.print("Previous month balance: $");
    double mes = consola.nextDouble();
    System.out.print("Income: $");
    double ing = consola.nextDouble();
    System.out.print("Expenses: $");
    double egr = consola.nextDouble();
    System.out.print("Issued checks: ");
    int cheques = consola.nextInt();
    System.out.print("What type of account do you have? [Savings(1)- Regular(2)- Business(3)]: ");
    int cuenta = consola.nextInt();
    double balance = mes -egr + ing -(cheques>5?(cheques-5)*13:0);
    double interes;
    switch (cuenta) {
      case 1:interes = Math.round((balance<10000? balance*0.075:0)*100)/100.0;
      System.out.println("\nMonthly interest: $"+interes+"\nTotal monthly balance is: $"+(balance-interes)); break;
      case 2:interes = Math.round((balance<15000? balance*0.075:0)*100)/100.0;
      System.out.println("\nMonthly interest: $"+interes+"\nTotal monthly balance is: $"+(balance-interes)); break;
      case 3: balance = mes -egr + ing -cheques*13;
      interes = Math.round((balance<20000? balance*0.075:0)*100)/100.0;
      System.out.println("\nMonthly interest: $"+interes+"\nTotal monthly balance is: $"+(balance-interes)); break;
      default: System.out.println("This is not a valid account selection"); break;
    }
  }
}

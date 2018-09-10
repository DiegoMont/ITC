import java.util.Scanner;
class LadderBuilder{
  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    System.out.print("Available big pieces: ");
    int piezaG = consola.nextInt();
    System.out.print("Available small pieces: ");
    int piezaP = consola.nextInt();
    System.out.print("Requested size of the ladder: ");
    int goalSize = consola.nextInt();
    if (goalSize/5<=piezaG && goalSize%5 <= piezaP || goalSize-piezaG*5 <= piezaP && goalSize-piezaG*5 +piezaP >=0) {
      System.out.println("You can build it.");
      System.out.println("And you have "+(piezaG-goalSize/5<0 ? 0: piezaG-goalSize/5)+" big pieces left and "+(piezaP-(goalSize-(goalSize/5>=piezaG?piezaG:goalSize/5)*5))+" small pieces left.");
    } else {
      System.out.println("You don't have enough pieces.");
    }
  }
}

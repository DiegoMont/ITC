import java.util.Scanner;
class Probabilities{
  public static int rollDice(){
    return (int) Math.floor(Math.random()*6)+1;
  }

  public static void checkProbability(int tests){
    int uno = 0;
    int dos = 0;
    int tres = 0;
    int cuatro = 0;
    int cinco = 0;
    int seis = 0;
    for (int i = 1; i <=tests; i++) {
      switch(rollDice()){
        case 1: uno++; break;
        case 2: dos++; break;
        case 3: tres++; break;
        case 4: cuatro++; break;
        case 5: cinco++; break;
        case 6: seis++; break;
      }
    }
    System.out.println("1: "+(Math.round(10000.0*uno/tests)/100.0)+"%");
    System.out.println("2: "+(Math.round(10000.0*dos/tests)/100.0)+"%");
    System.out.println("3: "+(Math.round(10000.0*tres/tests)/100.0)+"%");
    System.out.println("4: "+(Math.round(10000.0*cuatro/tests)/100.0)+"%");
    System.out.println("5: "+(Math.round(10000.0*cinco/tests)/100.0)+"%");
    System.out.println("6: "+(Math.round(10000.0*seis/tests)/100.0)+"%");
  }

  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    System.out.print("How many tests do you want to make? ");
    checkProbability(consola.nextInt());
  }
}

import java.util.Scanner;
class RoadrunnerRace{
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.println("Indicate a period of time in minutes:");
    double min = s.nextDouble();
    double distancia = min*57/60.0;
    System.out.println("The roadrunner has traveled "+distancia+" km at a speed of 57km/h.");
  }
}

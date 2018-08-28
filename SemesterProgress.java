import java.util.Scanner;
class SemesterProgress{
  public static void main(String[] args) {
    int numSu = 6; int numSe = 9; int numUn = 8;
    Scanner s = new Scanner(System.in);
    System.out.println("How many semesters have you completed?");
    int compS = s.nextInt();
    System.out.println("How many subjects have you failed?");
    int failS = s.nextInt();
    double totalU = numSu * numSe * numUn;
    System.out.println(totalU);
    double percent = (totalU - compS*numUn*numSu + failS*numUn) / totalU * 100;
    System.out.println("You are still missing " + percent + "% of your career.");
  }
}

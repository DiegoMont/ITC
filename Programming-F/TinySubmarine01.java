import java.util.Arrays;
import java.util.Scanner;
public class TinySubmarine01 {

    //Global variables
    public static int number_of_shots = 3;
    public static Scanner sc = new Scanner(System.in);
    public static String[] board = new String[10];
    public static String[] secretboard = new String[10];
    public static boolean play = true;
    public static int hits = 0;
    public static int rand;

    public static void main(String[] args) {
      init();
      while(play){
        shoot();
      }
      if (hits < 2) {
        System.out.println("You loose!");
        System.out.println(Arrays.toString(secretboard));
      }
    }

    public static void init(){
        for (int i = 0; i < board.length; i++) {
            board[i] = "?";
            secretboard[i] = "?";
        }

        rand = (int) Math.floor(Math.random()*9); //0-8
        secretboard[rand] = "*";
        secretboard[rand+1] = "*";

        //Print the board:
        System.out.println(Arrays.toString(board));

        //Print the secret board:
        //System.out.println(Arrays.toString(secretboard));
    }

    public static void shoot(){
      //Check if we still have any shots left:
      if(number_of_shots > 0){
        System.out.println("Where do you want to shoot? [0-9]");
        int shot = sc.nextInt();
        //Check if the user hit something...
        if(secretboard[shot] == "*" && board[shot] != "*"){
          System.out.println("Hit");
          board[shot] = "*";
          //Print public board
          System.out.println(Arrays.toString(board));
          //Increase hits
          hits++;
          if(hits == 2){
            System.out.println("You win!");
            play = false;
          }
        }else{
          if (shot == rand || shot == rand +1) {
            System.out.println("Missed! You already hit there");
          } else if (shot < rand-1 || shot > rand+2 || hits > 0) {
            System.out.println("Missed");
          } else {
            System.out.println("So close!");
          }
        }
        number_of_shots--;
      }else{
        play = false;
      }
    }
  }

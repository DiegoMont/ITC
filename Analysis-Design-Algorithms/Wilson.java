/*
Wilson!
Chuck Noland is a FedEx employee stranded on an uninhabited island after his plane crashed in the South Pacific and his desperate attempts to survive. His family and friends have started a desperate search to find him. They have a map of the area where the plane possible crashed and they are planning search first on the largest islands.
This map is represented as a matrix filled with zeros and ones where 0 represents sea and 1 represents land. Two cells are considered part of the same island if the are adjacent (top, bottom, left or right).
Input
  The first line contains the values of W, L. The width and length of the map
  The next W lines contain L values of 0 or 1 where 0 represents sea and 1 represents land
Output
  The coordinates (row, col) of the upper left and lower right corners of the smallest rectangle containing all the land points of the largest island
SAMPLE INPUT:
3 3
0 0 0
0 0 1
0 1 1
SAMPLE OUTPUT:
1 1 2 2
*/
import java.util.Scanner;
import java.util.LinkedList;
import java.awt.Point;

public class Wilson {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int h = sc.nextInt();
    int w = sc.nextInt();
    int[][] mapa = new int[h][w];
    for (int i = 0; i < h; i++)
      for (int j = 0; j < w; j++)
        mapa[i][j] = sc.nextInt();
    //imprimirMapa(mapa);

    boolean[][] visited = new boolean[h][w];
    int[] resultado = new int[5];

    for (int i = 0; i < h; i++)
      for (int j = 0; j < w; j++)
        if (mapa[i][j] == 1 && !visited[i][j]){
          int[] datosIsla = explorarIsla(mapa, visited, i, j);
          if (datosIsla[0] > resultado[0]) {
            resultado = datosIsla;
          }
        }
        else
          visited[i][j] = true;

    for (int i = 1; i < 5; i++) {
      System.out.print(resultado[i] + " ");
    }

    System.out.println("");

    sc.close();
  }

  private static int[] explorarIsla(int[][] mapa, boolean[][] visited, int i, int j){
    LinkedList<Point> queue = new LinkedList<>();
    visited[i][j] = true;
    queue.add(new Point(i, j));
    int area = 0;
    int limiteSuperior = i, limiteInferior = i, limiteIzquierdo = j, limiteDerecho = j;
    while(queue.size() != 0){
      Point s = queue.poll();//imprimir
      //System.out.println(s.toString() + ",");
      area++;
      //Checar sus vecinos
      if(s.x - 1 > -1 && !visited[s.x-1][s.y] && mapa[s.x-1][s.y] == 1){
        queue.add(new Point(s.x-1, s.y));
        visited[s.x-1][s.y] = true;
        limiteSuperior = Math.min(limiteSuperior, s.x-1);
      }
      if(s.x + 1 < mapa.length && !visited[s.x+1][s.y] && mapa[s.x+1][s.y] == 1){
        queue.add(new Point(s.x+1, s.y));
        visited[s.x+1][s.y] = true;
        limiteInferior = Math.max(limiteInferior, s.x+1);
      }
      if(s.y - 1 > -1 && !visited[s.x][s.y-1] && mapa[s.x][s.y-1] == 1){
        queue.add(new Point(s.x, s.y-1));
        visited[s.x][s.y-1] = true;
        limiteIzquierdo = Math.min(limiteIzquierdo, s.y-1);
      }
      if(s.y + 1 < mapa[s.x].length && !visited[s.x][s.y+1] && mapa[s.x][s.y+1] == 1){
        queue.add(new Point(s.x, s.y+1));
        visited[s.x][s.y+1] = true;
        limiteDerecho = Math.max(limiteDerecho, s.y+1);
      }
    }
    //System.out.println("La isla en (" + i + ", " + j + ") tiene un area de: " + area);
    //System.out.println(limiteSuperior + " " + limiteIzquierdo + " " + limiteInferior + " " + limiteDerecho);
    int[] resultados = {area, limiteSuperior, limiteIzquierdo, limiteInferior, limiteDerecho};
    return resultados;
  }

  private static void imprimirMapa(int[][] mapa){
    for (int i = 0; i < mapa.length; i++){
      for (int j = 0; j < mapa[0].length; j++){
        System.out.print(mapa[i][j] == 1 ? "* ": ". ");
      }
      System.out.println("");
    }
  }
}

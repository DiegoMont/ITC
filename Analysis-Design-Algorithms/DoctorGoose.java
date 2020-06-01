/*
Doctor Goose
Dr. Goose is a recognized doctor throughout the city. During this epidemic he is very active visiting patients in their homes to prevent them from going outside. However, he has a problem because sometimes he doesn't know where to go first.
To solve this problem, the doctor obtained a map with the transfer times to the most important points in the city, and a list with an evaluation of the severity of every patient (1 for a very serious case and 5 for a regular one). With these values, the doctor determines an evaluation for each patient multiplying the shortest transfer time by the severity of the case.
Given this information, help the doctor providing him a list with the 5 most important cases to solve (those with the lowest evaluation).
Input
  The first line contains the values of N, M, C, the number of important points in the city, the number of available roads, and the number of patients
  The next N lines contain a label for an important point in the city
  The next M lines describe the available roads. Every line contains the source and destination indices (1 based) of important points in the city and the estimated time to go from the source to the destination
  The next C lines contain the label of the patient's location and his severity score (between 1 and 5)
  The last line contains the label of the doctor's current position
Output
The location labels and the scores (one per line) of the 5 most important cases that can be visited from the current location
In case of having two elements with the same score, display them in alphabetical order of the label
SAMPLE INPUT:
11 16 8
ITESM-CCM
Airport
Xochimilco
Cuernavaca
Perisur
Azteca Stadium
UNAM
ITESM-SF
Mixcoac
Constituyentes
Zocalo
1 2 40
1 3 25
1 5 20
1 6 20
2 11 60
2 10 35
3 5 21
3 4 60
5 6 7
5 8 30
6 7 15
7 8 28
7 9 20
8 10 22
9 11 30
11 10 20
Airport 1
Constituyentes 2
UNAM 1
Perisur 5
ITESM-CCM 3
Cuernavaca 4
Mixcoac 5
Xochimilco 2
Zocalo
SAMPLE OUTPUT:
Constituyentes 40
UNAM 50
Airport 55
Mixcoac 150
Xochimilco 186
*/
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class DoctorGoose {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), m = sc.nextInt(), c = sc.nextInt(); sc.nextLine();
    HashMap<String, Integer> lugares = new HashMap<>();
    ArrayList<LinkedList<ArrayList<Integer>>> ciudad = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      lugares.put(sc.next(), i); sc.nextLine();
      ciudad.add(new LinkedList<>());
    }
    for (int i = 0; i < m; i++) {
      int origen = sc.nextInt() - 1, destino = sc.nextInt() - 1, tiempo = sc.nextInt(); sc.nextLine();
      ArrayList<Integer> nodo = new ArrayList<>();
      nodo.add(destino);
      nodo.add(tiempo);
      ArrayList<Integer> nodo2 = new ArrayList<>();
      nodo2.add(origen);
      nodo2.add(tiempo);
      ciudad.get(origen).add(nodo);
      ciudad.get(destino).add(nodo2);
    }
    Paciente[] pacientes = new Paciente[c];
    for (int i = 0; i < c; i++) {
      String ubicacionPaciente = sc.next();
      Paciente paciente = new Paciente(ubicacionPaciente, lugares.get(ubicacionPaciente), sc.nextInt()); sc.nextLine();
      //System.out.println(paciente.nombre + "   " + paciente.indice);
      pacientes[i] = paciente;
    }
    String origen = sc.next();
    sc.close();
    int origenNumerico = lugares.get(origen);
    //System.out.println("El origen es " + origenNumerico);
    //echarse el Dijkstra y multiplicar su score
    int[] distancias = dijkstra(origenNumerico, ciudad, n);
    //System.out.println(Arrays.toString(distancias));
    for(Paciente paciente: pacientes){
      //System.out.print("El score original es de " + paciente.score);
      paciente.score = paciente.score * distancias[paciente.indice];
      //System.out.println(" y el score de " + paciente.nombre + " es " + paciente.score);
    }
    bubbleSort(pacientes);
    for (int i = 0; i < c && i < 5; i++) {
      System.out.println(pacientes[i].nombre + " " + pacientes[i].score);
    }
  }

  private static int[] dijkstra(int origen, ArrayList<LinkedList<ArrayList<Integer>>> ciudad, int n){
    boolean[] visitado = new boolean[n];
    int[] distancias = new int[n];
    for(int i = 0; i < n; i++)
      distancias[i] = Integer.MAX_VALUE;
    distancias[origen] = 0;
    for (int count = 0; count < n-1; count++) {
      //cual tiene la distancia menor
      int min = Integer.MAX_VALUE, u = -1;
      for (int i = 0; i < n; i++) {
        if(!visitado[i] && distancias[i] <= min) {
          min = distancias[i];
          u = i;
        }
      }
      visitado[u] = true;
      for(ArrayList<Integer> info: ciudad.get(u)){
        if (!visitado[info.get(0)] && distancias[u] + info.get(1) < distancias[info.get(0)]) {
          distancias[info.get(0)] = distancias[u] + info.get(1);
        }
      }
    }
    return distancias;
  }

  private static void bubbleSort(Paciente[] pacientes) {
    int i = 1;
    while (pacientes.length > i) {
      int j = i;
      while (j > 0 && (pacientes[j-1].score > pacientes[j].score || (pacientes[j-1].score == pacientes[j].score && pacientes[j-1].nombre.compareTo(pacientes[j].nombre) > 0))) {
        swap(pacientes, j, j-1);
        j--;
      }
      i++;
    }
  }

  private static void swap(Paciente[] pacientes, int a, int b) {
    Paciente temp = pacientes[a];
    pacientes[a] = pacientes[b];
    pacientes[b] = temp;
  }
}

class Paciente {
  String nombre;
  int indice;
  int score;

  public Paciente(String nombre, int indice, int score){
    this.nombre = nombre;
    this.indice = indice;
    this.score = score;
  }
}

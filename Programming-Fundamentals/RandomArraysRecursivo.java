import java.util.Scanner;
class RandomArraysRecursivo{

  public static int[] arreglo;

  public static int asignarNumero(int j, int I){
    if (I==j&& arreglo[I]== arreglo[j]) {
      return 1;
    }else if (arreglo[I]==arreglo[j]) {
      return -2 + asignarNumero(j+1,I);
    } else {
      return 0 + asignarNumero(j+1,I);
    }
  }

  public static void main(String[] args) { long inicio = System.currentTimeMillis();
    Scanner consola = new Scanner(System.in);
    int sizeArreglo = 0;
    while(sizeArreglo < 1){
      System.out.print("Enter the size of the array: ");
      sizeArreglo = consola.nextInt();
    }
    arreglo = new int[sizeArreglo];
    int i = 0;
    while (i < sizeArreglo) {
      arreglo[i] = (int)Math.round(Math.random()*(sizeArreglo-1))+1;
      i += asignarNumero(0, i);
    }
    for(int h=0;h<sizeArreglo;h++){System.out.println(arreglo[h]);}
    System.out.println(System.currentTimeMillis()-inicio);
  }
}

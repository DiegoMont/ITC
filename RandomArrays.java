import java.util.Scanner;
class RandomArrays{
  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    int sizeArreglo = 0;
    while(sizeArreglo < 1){
      System.out.print("Enter the size of the array: ");
      sizeArreglo = consola.nextInt();
    }
    int []arreglo = new int[sizeArreglo];
    for (int i = 0; i<sizeArreglo; i++) {
      arreglo[i] = (int)Math.round(Math.random()*(sizeArreglo-1))+1;
      int j = 0;
      while(arreglo[i]!=arreglo[j]){
        j++;
      }
      if (j != i) {
        i--;
      }
    }
    for(int i=0;i<sizeArreglo;i++){
      System.out.println(arreglo[i]);
    }
  }
}

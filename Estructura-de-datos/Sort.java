public class Sort {
  public static void main(String[] args) {
    int[] arreglo = {5, 6, 2, 7, 3, 8, 1, 4};
    quickSort(arreglo);
    for (int x : arreglo) {
      System.out.println(x);
    }
  }

  public static void swap(int[] arreglo, int elemento1, int elemento2){
    int temporal = arreglo[elemento1];
    arreglo[elemento1] = arreglo[elemento2];
    arreglo[elemento2] = temporal;
  }

  public static void bubbleSort(int[] arreglo){
    for (int i = 0; i < arreglo.length-1; i++) {
      boolean swapped = false;
      for (int j = 0; j < arreglo.length-1; j++) {
        if (arreglo[j] > arreglo[i+1]) {
          swap(arreglo, j, i+1);
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }
  }

  public static void selectionSort(int[] arreglo){
    for (int i = 0; i < arreglo.length-1; i++) {
      int smallest = i;
      for (int j = i+1; j < arreglo.length; j++) {
        if (arreglo[j] < arreglo[smallest]) {
          smallest = j;
        }
      }
      swap(arreglo, i, smallest);
    }
  }

  public static void insertionSort(int[] arreglo){
    int i = 1;
    while (arreglo.length > i) {
      int j = i;
      while (j > 0 && arreglo[j-1] > arreglo[j]) {
        swap(arreglo, j, j-1);
        j--;
      }
      i++;
    }
  }

  public static void mergeSort(int[] arreglo){
    int aiuda;
  }

  public static void quickSort(int[] arreglo){
    comenzarQuickSort(arreglo, 0, arreglo.length-1);
  }

  public static void comenzarQuickSort(int[] arreglo, int izquierda, int derecha){
    int mid = (izquierda + derecha)/2;
    int i = izquierda;
    int j = derecha;
    while (i < j) {
      while (arreglo[i] < arreglo[mid]) {
        i++;
      }
      while (arreglo[j] > arreglo[mid]) {
        j--;
      }
      if (i <= j) {
        if (i != j) {
          swap(arreglo, i, j);
        }
        i++;
        j--;
      }
    }
    if (izquierda < j) {
      comenzarQuickSort(arreglo, izquierda, j);
    }
    if (derecha > i) {
      comenzarQuickSort(arreglo, i, derecha);
    }
  }
}

import java.util.Scanner;
class ArrayMenu{

  public static int[][] dimensionalArray;

  public static void printMatrix(int[][] arreglo){
    System.out.println();
    for (int i = 0; i<arreglo.length; i++) {
      System.out.print("[");
      for (int j = 0; j<arreglo[i].length-1; j++) {
        System.out.print(arreglo[i][j]+", ");
      }
      System.out.println(arreglo[i][arreglo[i].length-1]+"]");
    }
  }

  public static void switchColumns(int[][] arreglo, int column1, int column2){
    int[] a = new int[arreglo.length];
    for (int i = 0; i < arreglo.length; i++) {
      for (int j = 0; j < arreglo[i].length; j++) {
        if (column1 == j) {
          a[i]=dimensionalArray[i][j];
          dimensionalArray[i][column1] = arreglo[i][column2];
        } else if (column2 == j) {
          dimensionalArray[i][column2] = a[i];
        } else {
          dimensionalArray[i][j] = arreglo[i][j];
        }
      }
    }
    printMatrix(dimensionalArray);
  }

  public static void switchRows(int[][] arreglo, int row1, int row2){
    int[] b = arreglo[row1];
    for (int i = 0; i < arreglo.length; i++) {
      if (i == row1) {
        dimensionalArray[i]=arreglo[row2];
      } else if (i == row2) {
        dimensionalArray[i] = b;
      }
    }
    printMatrix(dimensionalArray);
  }

  public static void transporseMatrix(int[][] arreglo){
    for (int i = 0; i < arreglo.length; i++) {
      for (int j = 0; j < arreglo[i].length; j++) {
        dimensionalArray[j][i]=arreglo[i][j];
      }
    }
    printMatrix(dimensionalArray);
  }

  public static void main(String[] args) {
    Scanner consola = new Scanner(System.in);
    int n=0;
    int m=0;
    while(n <= 0){
      System.out.print("How many rows do you want the array to have: ");
      n = consola.nextInt();
    }
    while(m <= 0){
      System.out.print("How many columns do you want the array to have: ");
      m = consola.nextInt();
    }
    dimensionalArray = new int[n][m];
    for (int i = 0; i<n; i++) {
      for (int j = 0; j<m; j++) {
        dimensionalArray[i][j] = (int) Math.round(Math.random()*(n-1)+1);
      }
    }
    printMatrix(dimensionalArray);
    boolean salir = true;
    int[][] temporalArray = dimensionalArray;
    do {
      System.out.print("\n1. Switch columns\n2. Switch rows\n3. Transpose matrix\n4. End\n\nEnter the number of the option: ");

      switch (consola.nextInt()) {
        case 1:
        int col1 = -1;int col2 = -1;
        while (col1 < 0 || col2 < 0 || col1 >= m || col2 >= m || col1 > col2) {
          System.out.println("\nEnter the number of the columns you want to swap. Remember that the first column is column 0\nWrite the number of one column and press enter, then enter the other column number.");
          col1 = consola.nextInt();
          col2 = consola.nextInt();
        }
        switchColumns(temporalArray, col1, col2);
        break;
        case 2:
        int row1 = -1; int row2 = -1;
        while (row1 < 0 || row2 <= 0 || row1 >= n || row2 >= n || row1 > row2) {
          System.out.println("\nEnter the number of the rows you want to swap. Remember that the first row is row 0\nWrite the number of one row and press enter, then enter the other row number.");
          row1 = consola.nextInt();
          row2 = consola.nextInt();
        }
        switchRows(temporalArray, row1, row2);
        break;
        case 3: transporseMatrix(temporalArray); break;
        case 4: salir=false;break;
        default: System.out.println("That is not a valid option");
      }
    } while (salir);
  }
}

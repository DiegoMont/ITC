import java.util.Scanner;
class Project{
  public static Scanner consola = new Scanner(System.in);

  public static void printMatrix(String[][] arreglo){
    if (arreglo[0][0]==null) {
      System.out.println("There is no students' information available");
    } else {
      int sizeName=arreglo[0][0].length();
      for(String[] student: arreglo){
        if(sizeName<student[0].length()){
          sizeName = student[0].length();
        }
      }
      System.out.print("Name");
      for(int i = 4; i<= sizeName; i++){
        System.out.print(" ");
      }
      System.out.println("HW1 HW2 HW3 HW4 ParEx1 ParEx2 FinalProj FinalEx Final");
      for(String[] student: arreglo){
        System.out.print(student[0]);
        for(int i = student[0].length(); i <= sizeName; i++){
          System.out.print(" ");
        }
        for(int i =1; i <= 4; i++){
          for(int j =student[i].length(); j < 3; j++){
            System.out.print(" ");
          }
          System.out.print(student[i]+" ");
        }
        for(int i =5; i <= 6; i++){
          for(int j =student[i].length(); j < 6; j++){
            System.out.print(" ");
          }
          System.out.print(student[i]+" ");
        }
        for(int i =student[7].length(); i < 9; i++){
          System.out.print(" ");
        }
        System.out.print(student[7]+" ");
        for(int i =student[8].length(); i < 7; i++){
          System.out.print(" ");
        }
        System.out.print(student[8]+" ");
        for(int i =student[9].length(); i < 5; i++){
          System.out.print(" ");
        }
        System.out.println(student[9]);
      }
    }
  }

  public static String[] addData(String[] grades) {
    String[] nuevoArray = new String[10];
    nuevoArray[0] = grades[0];
    int i;
    for (i = 1;i <=4 ; i++) {
      System.out.print("Enter grade of homework "+i+": ");
      nuevoArray[i] = consola.next();
    }
    for (i = 5; i <=  6; i++) {
      System.out.print("Enter grade of partial exam "+(i-4)+": ");
      nuevoArray[i] = consola.next();
    }
    System.out.print("Enter grade of the final project: ");
    nuevoArray[7] = consola.next();
    System.out.print("Enter grade of the final exam: ");
    nuevoArray[8] = consola.next();
    return nuevoArray;
  }

  public static String calculateFinalGrade(String[] califs){
    int tasks, partialExams, project, examF;
    tasks = Integer.valueOf(califs[1]).intValue() + Integer.valueOf(califs[2]).intValue() + Integer.valueOf(califs[3]).intValue() + Integer.valueOf(califs[4]).intValue();
    tasks = (int)Math.round(tasks*0.1);
    partialExams = Integer.valueOf(califs[5]).intValue() + Integer.valueOf(califs[6]).intValue();
    partialExams = (int)Math.round(partialExams * 0.075);
    project = (int)Math.round(Integer.valueOf(califs[7]).intValue()*0.25);
    examF = (int)Math.round(Integer.valueOf(califs[8]).intValue()*0.2);
    return "" +(tasks + partialExams + project + examF);
  }

  public static void studentReport(String[] notas){
    System.out.print("\n1. Show final grade\n2. Show activity note\n\nEnter option:");
    if (consola.nextInt() == 2) {
      System.out.print("Select an activity (1-8): ");
      switch (consola.next()) {
        case "1": System.out.println("In homework 1 "+notas[0]+" got "+notas[1]); break;
        case "2": System.out.println("In homework 2 "+notas[0]+" got "+notas[2]); break;
        case "3": System.out.println("In homework 3 "+notas[0]+" got "+notas[3]); break;
        case "4": System.out.println("In homework 4 "+notas[0]+" got "+notas[4]); break;
        case "5": System.out.println("In partial exam 1 "+notas[0]+" got "+notas[5]); break;
        case "6": System.out.println("In partial exam 2 "+notas[0]+" got "+notas[6]); break;
        case "7": System.out.println("In the final project "+notas[0]+" got "+notas[7]); break;
        case "8": System.out.println("In the final exam 1 "+notas[0]+" got "+notas[8]); break;
        default:
          System.out.println("Not a valid option");
      }
    } else {
      System.out.println("The final average of "+notas[0]+" is "+notas[9]);
    }
  }

  public static void showStats(int numAct, String[][] grades){
    int promedio = 0;
    double varianza = 0;
    double desviacion = 0;
    for (String[] alumno: grades) {
      promedio += Integer.valueOf(alumno[numAct]).intValue();
    }
    promedio = Math.round(promedio/grades.length);
    System.out.println("\nThe average of the activity is "+promedio);
    for (String[] alumno: grades) {
      varianza += Math.pow(Math.abs(Integer.valueOf(alumno[numAct]).intValue()-promedio), 2);
      desviacion += Math.pow(Math.abs(Integer.valueOf(alumno[numAct]).intValue()-promedio), 2);
    }
    varianza = varianza/(grades.length-1.0);
    desviacion = Math.sqrt(desviacion/grades.length);
    System.out.println("\nThe variance of the activity is "+varianza);
    System.out.println("\nThe standard deviation of the activity is "+desviacion);
  }

  public static void groupStats(String[][] grupo){
    int promedio = 0;
    double varianza = 0;
    double desviacion = 0;
    int pass = 0;
    int passnicht = 0;
    for (String[] alumno: grupo) {
      promedio += Integer.valueOf(alumno[9]).intValue();
      if (Integer.valueOf(alumno[9]).intValue() >= 70) {
        pass++;
      } else {
        passnicht++;
      }
    }
    promedio = Math.round(promedio/grupo.length);
    System.out.println("\nThe average of the group is "+promedio);
    for (String[] alumno: grupo) {
      varianza += Math.pow(Math.abs(Integer.valueOf(alumno[9]).intValue()-promedio), 2);
      desviacion += Math.pow(Math.abs(Integer.valueOf(alumno[9]).intValue()-promedio), 2);
    }
    varianza = varianza/(grupo.length-1.0);
    desviacion = Math.sqrt(desviacion/grupo.length);
    System.out.println("\nThe variance of the group is "+varianza);
    System.out.println("\nThe standard deviation of the group is "+desviacion);
    System.out.println("\n"+pass+" students passed and "+passnicht+" students failed");
  }

  public static void main(String[] args) {
    String[][] matrix = new String[20][10];
    for (int i = 0; i < matrix.length; i++) {
      matrix[i][0] = "Student" + (i+1);
      for (int j = 1;j < matrix[i].length-1 ; j++) {
        matrix[i][j] = "" + (Math.round(Math.random()*50)+50);
      }
    }
    boolean exit = true;
    while (exit) {
      for (int i = 0; i < matrix.length; i++) {
        matrix[i][9] = calculateFinalGrade(matrix[i]);
      }
      System.out.print("\n1. Add student to the list\n2. Enter grades of student\n3. View a grade of a student\n4. View stats of an activity\n5. Group information\n6. Print all data\n7. Exit\n\nEnter the option you want to choose: ");
      int a;
      switch (consola.next()) {
        case "1": System.out.print("Which student do you want to add (1-20): ");
                a = consola.nextInt();
                System.out.print("Enter the name of the student: ");
                matrix[a-1][0] = consola.next();
                break;
        case "2": System.out.print("Select a student (1-20): "); a = consola.nextInt()-1; matrix[a]=addData(matrix[a]);break;
        case "3": System.out.print("Select a student (1-20): "); a = consola.nextInt()-1; studentReport(matrix[a]); break;
        case "4": System.out.print("Select an activity (1-9): ");a = consola.nextInt(); showStats(a, matrix);break;
        case "5": groupStats(matrix);break;
        case "6": printMatrix(matrix);break;
        case "7": exit = false; break;
        default: System.out.print("That is not a valid option, try again: ");
      }
    }
  }
}

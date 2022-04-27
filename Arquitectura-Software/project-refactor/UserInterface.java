import java.util.Scanner;

public class UserInterface {
    public static final int STUDENTS_NUM = 20;

    private Scanner sc;
    private Student[] students;

    public UserInterface() {
        sc = new Scanner(System.in);
        students = new Student[STUDENTS_NUM];
        createStudents();
    }

    public startApp() {
        int userOption = -1;
        while(userOption != 7) {
            printMainMenu();
            userOption = sc.nextInt();
            if(userOption == 1)
                addNewStudent();
            else if(userOption == 2)
                modifyGrades();
            else if(userOption == 3)
                viewGrades();
            else if(userOption == 4)
                viewActivityStats();
            else if(userOption == 5)
                groupInformation();
            else if(userOption == 6)
                printData();
            else if(userOption != 7)
                System.out.println("That is not a valid option, try again: ");
        }
    }

    private createStudents() {
        for (int i = 0; i < students.length; i++) {
           students[i] = new Student("Student" + (i+1));
        }
    }

    private void printMainMenu() {
        System.out.println("\n1. Add student to the list");
        System.out.println("2. Enter grades of student");
        System.out.println("3. View a grade of a student");
        System.out.println("4. View stats of an activity");
        System.out.println("5. Group information");
        System.out.println("6. Print all data");
        System.out.println("7. Exit");
        System.out.print("\nEnter the option you want to choose: ");
    }

    private void addNewStudent() {
        Student s = selectStudent();
        System.out.print("Enter the name of the student: ");
        String name = sc.nextLine();
        s.name = name;
    }

    private void modifyGrades() {
        Student s = selectStudent();
        for (String activity : students.COURSE_ACTIVITIES) {
            System.out.print("Enter grade of " + activity + ": ");
            int newGrade = sc.nextInt();
            s.setActivityGrade(activity, newGrade);
        }
    }

    private void viewGrades() {
        Student s = selectStudent();
        printGradeDetailsMenu();
        int input = sc.nextInt();
        if(input == 2) {
            System.out.print("Select an activity (1-" + s.COURSE_ACTIVITIES.length + "): ");
            input = sc.nextInt();
            String activity = s.COURSE_ACTIVITIES[input-1];
            System.out.println("In " + activity + " " + s.name + " got " + s.getActivityGrade(activity));
        } else
            System.out.println("The final average of "+s.name+" is "+s.getFinalGrade());
    }

    private void viewActivityStats() {
        System.out.print("Select an activity (1-" + (Student.COURSE_ACTIVITIES.length+1) + "):");
    }

    private Student selectStudent() {
        System.out.print("Select a student (1-" + STUDENTS_NUM + "): ");
        int studentIndex = sc.nextInt();
        return students[studentIndex];
    }

    private void printGradeDetailsMenu() {
        System.out.println("\n1. Show final grade");
        System.out.println("2. Show activity note");
        System.out.print("\nEnter option: ");
    }
}

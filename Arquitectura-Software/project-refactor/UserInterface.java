import java.util.Scanner;

public class UserInterface {
    private Scanner sc;
    private Group group;

    public UserInterface() {
        sc = new Scanner(System.in);
        group = new Group();
    }

    public void startApp() {
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
        String name = sc.next();
        s.name = name;
    }

    private void modifyGrades() {
        Student s = selectStudent();
        for (String activity : Student.COURSE_ACTIVITIES) {
            System.out.print("Enter grade of " + activity + ": ");
            int newGrade = sc.nextInt();
            try {
                s.setActivityGrade(activity, newGrade);
            } catch(Exception e) {}
        }
    }

    private void viewGrades() {
        Student s = selectStudent();
        printGradeDetailsMenu();
        int input = sc.nextInt();
        if(input == 2) {
            System.out.print("Select an activity (1-" + Student.COURSE_ACTIVITIES.length + "): ");
            input = sc.nextInt();
            String activity = Student.COURSE_ACTIVITIES[input-1];
            System.out.println("In " + activity + " " + s.name + " got " + s.getActivityGrade(activity));
        } else
            System.out.println("The final average of " + s.name + " is " + s.getFinalGrade());
    }

    private void viewActivityStats() {
        final int finalGradeOption = Student.COURSE_ACTIVITIES.length+1;
        System.out.print("Select an activity (1-" + finalGradeOption + "):");
        int option = sc.nextInt();
        ActivityStats stats;
        if (option == finalGradeOption)
            stats = group.getGradeStats(Student.FINAL_GRADE);
        else
            stats = group.getGradeStats(Student.COURSE_ACTIVITIES[option-1]);
        System.out.println("\nThe average of the activity is " + stats.average);
        System.out.println("The variance of the activity is " + stats.variance);
        System.out.println("The standard deviation of the activity is " + stats.stdDeviation + "\n");
    }

    private void groupInformation() {
        ActivityStats stats = group.getGradeStats(Student.FINAL_GRADE);
        System.out.println("\nThe average of the group is " + stats.average);
        System.out.println("The variance of the group is " + stats.variance);
        System.out.println("The standard deviation of the group is " + stats.stdDeviation);
        System.out.println(stats.passingGrades +" students passed and "+ stats.failingGrades +" students failed\n");
    }

    private void printData() {
        int maxNameLength = 0;
        for (int i = 0; i < group.STUDENTS_NUM; i++) {
            int nameLength = group.getStudent(i).name.length();
            if(nameLength > maxNameLength)
                maxNameLength = nameLength;
        }
        System.out.print("Name");
        for(int i = 4; i<= maxNameLength; i++)
            System.out.print(" ");
        for (String actName: Student.COURSE_ACTIVITIES)
            System.out.print(actName + ' ');
        System.out.println("");
        for (int i = 0; i < group.STUDENTS_NUM; i++) {
            printStudent(group.getStudent(i), maxNameLength);
        }
    }

    private Student selectStudent() {
        System.out.print("Select a student (1-" + Group.STUDENTS_NUM + "): ");
        int studentIndex = sc.nextInt() - 1;
        return group.getStudent(studentIndex);
    }

    private void printGradeDetailsMenu() {
        System.out.println("\n1. Show final grade");
        System.out.println("2. Show activity note");
        System.out.print("\nEnter option: ");
    }

    private void printStudent(Student s, int maxNameLength) {
        System.out.print(s.name);
        fillWithSpaces(s.name, maxNameLength);
        System.out.print(" ");
        for (String actName : Student.COURSE_ACTIVITIES) {
            int grade = s.getActivityGrade(actName);
            String gradeText = grade + "";
            System.out.print(gradeText);
            fillWithSpaces(gradeText, actName.length());
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    private void fillWithSpaces(String s, int totalSpace) {
        for (int i = s.length(); i < totalSpace; i++)
            System.out.print(" ");
    }
}

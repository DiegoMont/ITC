public class Group {
    public static final int STUDENTS_NUM = 20;
    public static final int PASS_GRADE = 70;

    private Student[] students;

    public Group() {
        students = new Student[STUDENTS_NUM];
        createStudents();
    }

    public Student getStudent(int i) {
        return students[i];
    }

    public ActivityStats getGradeStats(String gradeCategory) {
        ActivityStats stats = new ActivityStats();
        for (Student student : students)
            stats.average += gradeCategory == Student.FINAL_GRADE ? student.getFinalGrade(): student.getActivityGrade(gradeCategory);
        stats.average /= STUDENTS_NUM;
        for (Student student : students) {
            int grade = gradeCategory == Student.FINAL_GRADE ? student.getFinalGrade(): student.getActivityGrade(gradeCategory);
            stats.variance += Math.pow(Math.abs(grade - stats.average), 2);
            if(grade < PASS_GRADE)
                stats.failingGrades++;
            else
                stats.passingGrades++;
        }
        stats.variance /= STUDENTS_NUM - 1;
        stats.stdDeviation = Math.sqrt(stats.variance);
        return stats;
    }

    private void createStudents() {
        for (int i = 0; i < students.length; i++) {
           students[i] = new Student("Student" + (i+1));
        }
    }
}

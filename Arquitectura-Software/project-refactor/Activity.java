public class Activity {
    public static final int MAX_GRADE = 100;
    public static final int MIN_GRADE = 0;
    
    public String name;
    private int grade;

    public Activity(String name, int grade) {
        this.name = name;
        setGrade(grade);
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) throws Exception {
        if(grade > MAX_GRADE || grade < MIN_GRADE) {
            String errorMsg = grade + " is not a valid grade";
            throw new Exception(errorMsg);
        }
        this.grade = grade;
    }
}

public class Activity {
    public static final int MAX_GRADE = 100;
    public static final int MIN_GRADE = 0;
    
    private int grade;

    public Activity(int grade) {
        try {
            setGrade(grade);
        } catch(Exception e) {}
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

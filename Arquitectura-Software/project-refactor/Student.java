import java.util.Hashtable;

public class Student {
    public static final String[] COURSE_ACTIVITIES = {"HW1", "HW2", "HW3", "HW4", "ParEx1", "ParEx2", "FinalProj", "FinalEx"};
    public static final String FINAL_GRADE = "Final";

    public String name;
    private Hashtable<String, Activity> activities;
    private int finalGrade;
    
    public Student(String name) {
        this.name = name;
        activities = new Hashtable<>(COURSE_ACTIVITIES.length);
        setActivitiesAndFinalGrade();
    }

    public int getActivityGrade(String actName) {
        Activity activity = activities.get(actName);
        return activity.getGrade();
    }

    public void setActivityGrade(String actName, int grade) throws Exception {
        Activity activity = activities.get(actName);
        activity.setGrade(grade);
        setFinalGrade();
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    private void setActivitiesAndFinalGrade() {
        int gradeSum = 0;
        for (String activity : COURSE_ACTIVITIES) {
            int randomGrade = (int) (Math.round(Math.random()*50)+50);
            activities.put(activity, new Activity(randomGrade));
            gradeSum += randomGrade;
        }
        finalGrade = gradeSum / COURSE_ACTIVITIES.length;
    }

    private void setFinalGrade() {
        int gradeSum = 0;
        for (String activity : COURSE_ACTIVITIES)
            gradeSum += activities.get(activity).getGrade();
        finalGrade = gradeSum / COURSE_ACTIVITIES.length;
    }
}

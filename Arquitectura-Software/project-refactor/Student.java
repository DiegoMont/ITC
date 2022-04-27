import java.util.Hashtable;

public class Student {
    public static final String[] COURSE_ACTIVITIES = ["HW1", "HW2", "HW3", "HW4", "ParEx1", "ParEx2", "FinalProj", "FinalEx"]

    public String name;
    private Hashtable<Activity> activities;
    private int finalGrade;
    
    public Student(String name) {
        this.name = name;
        activities = new Hashtable<>(COURSE_ACTIVITIES.length);
        setActivitiesAndFinalGrade();
    }

    public getActivityGrade(String activity) {
        Activity activity = activities.get(actName);
        return activity.getGrade();
    }

    public setActivityGrade(String actName, int grade) {
        Activity activity = activities.get(actName);
        activity.setGrade(grade);
    }

    private setActivitiesAndFinalGrade() {
        int gradeSum = 0;
        for (String activity : COURSE_ACTIVITIES) {
            int randomGrade = (Math.round(Math.random()*50)+50);
            activities.put(new Activity(activity, randomGrade));
            gradeSum += randomGrade;
        }
        finalGrade = gradeSum / COURSE_ACTIVITIES.length;
    }

    private getFinalGrade() {
        return finalGrade
    }

    private setFinalGrade() {
        int gradeSum = 0;
        for (String activity : COURSE_ACTIVITIES)
            gradeSum += activities.get(activity).getGrade();
        finalGrade = gradeSum / COURSE_ACTIVITIES.length;
    }
}

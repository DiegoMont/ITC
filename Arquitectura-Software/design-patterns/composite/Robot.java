import java.util.LinkedList;

public class Robot {

    LinkedList<Mechanism> mechanisms;

    public Robot() {
        mechanisms = new LinkedList<>();
        Chasis chasis = new Chasis(1, 2);
        mechanisms.add(chasis);
        mechanisms.add(new ColorSensor(0));
    }

    public void setupHardware() {
        for (Mechanism mechanism: mechanisms) {
            mechanism.initializeHardware();
        }
    }

    public void printStatus() {
        System.out.println("CURRENT ROBOT STATUS:");
        for (Mechanism mechanism: mechanisms)
            System.out.println(mechanism.getStatus());
        System.out.println("#######");
    }
}
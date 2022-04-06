import java.util.Random;

public class FieldLocator {
    double lastPosition;
    public FieldLocator() {
        lastPosition = 0;
    }
    public double integrateMotion() {
        Random r = new Random();
        long waitUntil = System.currentTimeMillis() + r.nextLong(1000);
        while(System.currentTimeMillis() < waitUntil);
        lastPosition += r.nextInt(10);
        return lastPosition;
    }
}

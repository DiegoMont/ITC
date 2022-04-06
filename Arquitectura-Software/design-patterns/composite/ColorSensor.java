import java.util.Random;

public class ColorSensor implements Mechanism {

    int analogPort;
    boolean lightOn;

    public ColorSensor(int analogPort) {
        this.analogPort = analogPort;
        lightOn = true;
    }

    public void initializeHardware() {
        System.out.println("Connected to analog port " + analogPort);
    }

    public String getStatus() {
        Random r = new Random();
        return String.format("The sensor light is %s and the current reading is %.3f\n", (lightOn ? "on": "off"), r.nextDouble());
    }
}

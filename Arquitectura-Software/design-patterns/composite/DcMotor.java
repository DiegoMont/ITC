public class DcMotor implements Mechanism {
    double power;
    int port;

    DcMotor(int port) {
        this.port = port;
    }

    public void initializeHardware() {
        System.out.println("Connected to port " + port);
        power = 0;
    }

    public String getStatus() {
        return String.format("Current power is %.2f\n", power);
    }
}

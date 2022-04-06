public class Chasis implements Mechanism {

    public DcMotor leftDrive;
    public DcMotor rightDrive;

    public Chasis(int port1, int port2) {
        leftDrive = new DcMotor(port1);
        rightDrive = new DcMotor(port2);
    }

    public void initializeHardware() {
        leftDrive.initializeHardware();
        rightDrive.initializeHardware();
    }

    public String getStatus() {
        String status = "Left drive: " + leftDrive.getStatus();
        status += "Right drive: " + rightDrive.getStatus();
        return status;
    }
}

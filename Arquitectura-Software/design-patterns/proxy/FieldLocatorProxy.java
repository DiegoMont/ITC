public class FieldLocatorProxy {

    FieldLocator fl;
    long lastPositionUpdate;
    double position;
    final long UPDATE_INTERVAL = 250;

    public FieldLocatorProxy() {
        fl = new FieldLocator();
        position = 0;
        lastPositionUpdate = System.currentTimeMillis();
    }

    public double integrateMotion() {
        long current = System.currentTimeMillis();
        if (lastPositionUpdate + UPDATE_INTERVAL < current) {
            position = fl.integrateMotion();
            lastPositionUpdate = current;
        }
        return position;
    }
}

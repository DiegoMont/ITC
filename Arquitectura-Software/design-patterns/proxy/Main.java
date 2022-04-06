public class Main {
    public static void main(String[] args) {
        System.out.println("Sin el proxy");
        printCurrent();
        FieldLocator fl = new FieldLocator();
        FieldLocatorProxy proxy = new FieldLocatorProxy();
        
        System.out.println(fl.integrateMotion());
        System.out.println(fl.integrateMotion());
        System.out.println(fl.integrateMotion());
        printCurrent();

        System.out.println(proxy.integrateMotion());
        System.out.println(proxy.integrateMotion());
        System.out.println(proxy.integrateMotion());
        printCurrent();
    }

    static void printCurrent() {
        System.out.println(System.currentTimeMillis());
    }
}

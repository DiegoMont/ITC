class ShippingCostCalculator {
    public static final double TAXES = 0.0825;
    Package[] packages;

    public double getTotalCost() {
        return this.getShipmentCost() * TAXES;
    }

    private double getShipmentCost() {
        double cost = 0;
        for (Package package: packages) {
            if(package.size.equasl("SMALL"))
                cost += 10;
            else if(package.size.equals("MEDIUM"))
                cost += 35;
            else if(package.size.equals("BIG"))
                cost += 50;
            else
                throw new Exception("Invalid package size");
        }
    }
}

class Package {
    public String size;
}

class SmallPackage extends Package {
    public SmallPackage() {
        this.size = "SMALL";
    }
}

class MediumPackage extends Package {
    public MediumPackage() {
        this.size = "MEDIUM";
    }
}

class BigPackage extends Package {
    public BigPackage() {
        this.size = "BIG";
    }
}

// TODO: Add ExtraBigPackage class
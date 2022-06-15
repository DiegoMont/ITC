class MapRenderer {
    byte[][] map;

    public void addGeometriesToMap(GeoJSON[] elements) {
        for (GeoJSON geoJSON : elements) {
            double[] coordinates = geoJSON.getCoordinates();
            map[coordinates[0]][coordinates[1]] = 1;
        }
    }
}

class GeoJSON {
    double x, y;

    public GeoJSON(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double[] getCoordinates() {
        double[] coordinates = {x, y};
        return coordinates;
    }
}

// TODO: render SDO_Geometry in map

class SdoGeometry {
    double[] coordinates;

    public SdoGeometry(double x, double y) {
        coordinates = new double[2];
        coordinates[0] = x;
        coordinates[1] = y;
    }
}

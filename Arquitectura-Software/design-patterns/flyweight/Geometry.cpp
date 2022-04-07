#ifndef GEOMETRY_CLASS
#define GEOMETRY_CLASS
#include <stdio.h>

struct Point {
    double x;
    double y;
    double z;
};

class Geometry {
    public:
    int type;
    int srid;
    Point* point;
    int* elem_info;
    double* ordinates;

    double getArea() {
        double b = ordinates[2] - ordinates[0];
        double h = ordinates[3] - ordinates[1];
        double area =b * h;
        return area;
    }

    void printArea() {
        double area = getArea();
        printf("The area is %.2lf m2\n", area);
    }
};

#endif

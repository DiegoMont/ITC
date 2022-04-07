#include <iostream>

#include "Geometry.cpp"
#include "BoxFactory.cpp"

int main() {
    BoxFactory flyweigthFactory;
    double boxOrdinates1[] = {1, 1, 5, 7};
    double boxOrdinates2[] = {7, 5, 10, 10};
    double boxOrdinates3[] = {2, 4, 5, 13};
    double boxOrdinates4[] = {10, 3, 13, 9};
    Geometry* box;
    box = flyweigthFactory.getBox(boxOrdinates1);
    box->printArea();
    box = flyweigthFactory.getBox(boxOrdinates2);
    box->printArea();
    box = flyweigthFactory.getBox(boxOrdinates3);
    box->printArea();
    box = flyweigthFactory.getBox(boxOrdinates4);
    box->printArea();
}
#include "Geometry.cpp"

class BoxFactory {
    public:
    int* BOX_INFO;
    private:
    Geometry** instances;
    int newestInstanceIndex;
    const int MAX_INSTANCES = 3;
    public:
    BoxFactory() {
        BOX_INFO = new int[3]{1, 1003, 3};
        instances = new Geometry*[MAX_INSTANCES];
        newestInstanceIndex = -1;
        for (size_t i = 0; i < MAX_INSTANCES; i++)
            instances[i] = new Geometry{2003, 0, nullptr, BOX_INFO, nullptr};
    }

    Geometry* getBox(double* ordinates) {
        newestInstanceIndex = (newestInstanceIndex + 1) % MAX_INSTANCES;
        Geometry* geometry = instances[newestInstanceIndex];
        geometry->ordinates = ordinates;
        return geometry;
    }

};

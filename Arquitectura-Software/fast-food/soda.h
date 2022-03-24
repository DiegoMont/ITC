#ifndef SODA_CLASS
#define SODA_CLASS
#include "meal.h"

class Soda: public Meal {
    public:
    Soda(std::string name, double price): Meal(name, price){}

    void setAsComboItem() {
        this->name += " combo";
        this->price *= 0.8;
    }
};

#endif

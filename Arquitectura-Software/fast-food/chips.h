#ifndef CHIPS_CLASS
#define CHIPS_CLASS
#include "meal.h"

class Chips: public Meal {
    public:
    Chips(std::string name, double price): Meal(name, price){}

    void setAsComboItem() {
        this->name += " combo";
        this->price *= 0.8;
    }
};

#endif

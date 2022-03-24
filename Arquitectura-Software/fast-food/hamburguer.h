#include "meal.h"

class Hamburguer: public Meal {
    public:
    Hamburguer(): Meal("Hamburguesa individual", 40){}

    void setAsComboItem() {
        this->name = "Hamburguesa combo";
        this->price = 35;
    }
};

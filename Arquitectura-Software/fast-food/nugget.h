#include "meal.h"

class Nugget: public Meal {
    public:
    Nugget(): Meal("Nuggets individuales", 38){}

    void setAsComboItem() {
        this->name = "Nuggets combo";
        this->price = 32;
    }
};

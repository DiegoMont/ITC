#ifndef MEAL_CLASS
#define MEAL_CLASS
#include <iostream>
#include <string>

class Meal {
    protected:
    std::string name;
    double price;

    public:
    Meal(std::string name, double price) {
        this->name = name;
        this->price = price;
    }

    std::string getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    void setAsComboItem();

    void print() {
        std::cout << name << " $" << price << "\n";
    }
};
#endif

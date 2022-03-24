#include <vector>
#include "meal.h"

class Order {
    public:
    std::vector<Meal> items;
    double price;

    Order(std::vector<Meal> items) {
        this->items = items;
        this->calculatePrice();
    }

    void print() {
        for(Meal meal: items)
            meal.print();
        std::cout << "Total: $" << price << "\n";
    }

    private:
    void calculatePrice() {
        double totalPrice = 0;
        for (Meal meal: items) {
            totalPrice += meal.getPrice();
        }
        this->price = totalPrice;
    }
};

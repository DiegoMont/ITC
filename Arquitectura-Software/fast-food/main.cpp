/*
Diego Montaño Martínez  A01651308
Patrones Abstract Factory y Builder Pattern
*/

#include <iostream>
#include <vector>

#include "orderbuilder.h"

void printOrders(std::vector<Order*> orders);

int main() {
    std::vector<Order*> orders;

    std::cout << "El cliente 1 pidió papas grandes\n";
    orders.push_back(OrderBuilder::createIndividualOrder(PAPAS_GRANDES));

    std::cout << "El cliente 2 pidió hamburguesa\n";
    orders.push_back(OrderBuilder::createIndividualOrder(HAMBURGUESA));

    std::cout << "El cliente 3 pidió el combo 1\n";
    orders.push_back(OrderBuilder::createComboOrder(COMBO1));

    std::cout << "El cliente 4 pidió un refresco mediano\n";
    orders.push_back(OrderBuilder::createIndividualOrder(REFRESCO_MEDIANO));

    std::cout << "El cliente 5 pidió el combo 5\n";
    orders.push_back(OrderBuilder::createComboOrder(COMBO5));

    printOrders(orders);
}

void printOrders(std::vector<Order*> orders) {
    std::cout << "\n\nDETALLE DE ORDENES\n";
    for(Order* order: orders) {
        order->print();
        std::cout << "\n";
    }
}

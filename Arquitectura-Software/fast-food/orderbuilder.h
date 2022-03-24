#include <vector>
#include "order.h"
#include "hamburguer.h"
#include "chips.h"
#include "soda.h"
#include "nugget.h"
#include "chipfactory.h"
#include "sodafactory.h"
#include "menuoptions.h"
#include "mealsizes.h"

/* Estas funciones permiten crear ordenes a partir de las ya definidas por el usuario, estas funciones aseguran que todos los combos sean válidos, contengan los objetos correctos y sus precios sean correctos */

namespace OrderBuilder {

    void makeCombo(std::vector<Meal> items) {
        for(Meal item: items);
            //item.setAsComboItem();
    }

    Order* createIndividualOrder(enum MenuOptions option) {
        if(option == PAPAS_GRANDES) {
            std::vector<Meal> orderItems;
            orderItems.push_back(ChipsFactory::createChips(BIG));
            return new Order(orderItems);
        } else if(option == PAPAS_MEDIANAS) {
            std::vector<Meal> orderItems;
            orderItems.push_back(ChipsFactory::createChips(MEDIUM));
            return new Order(orderItems);
        } else if(option == PAPAS_CHICAS) {
            std::vector<Meal> orderItems;
            Chips mediumChips(ChipsFactory::createChips(SMALL));
            orderItems.push_back(mediumChips);
            return new Order(orderItems);
        } else if(option == HAMBURGUESA) {
            std::vector<Meal> orderItems;
            Hamburguer hamburguer;
            orderItems.push_back(hamburguer);
            return new Order(orderItems);
        } else if(option == NUGGETS) {
            std::vector<Meal> orderItems;
            Nugget nuggets;
            orderItems.push_back(nuggets);
            return new Order(orderItems);
        } else if(option == REFRESCO_GRANDE) {
            std::vector<Meal> orderItems;
            orderItems.push_back(SodaFactory::createSoda(BIG));
            return new Order(orderItems);
        } else if(option == REFRESCO_MEDIANO) {
            std::vector<Meal> orderItems;
            orderItems.push_back(SodaFactory::createSoda(MEDIUM));
            return new Order(orderItems);
        } else if(option == REFRESCO_CHICO) {
            std::vector<Meal> orderItems;
            orderItems.push_back(SodaFactory::createSoda(SMALL));
            return new Order(orderItems);
        }
        return nullptr;
    }

    Order* createComboOrder(enum MenuOptions option) {
        if(option == COMBO1) {
            std::vector<Meal> orderItems;
            Hamburguer h;
            Chips c = ChipsFactory::createChips(BIG);
            Soda bigSoda = SodaFactory::createSoda(BIG);
            orderItems.push_back(bigSoda);
            orderItems.push_back(h);
            orderItems.push_back(c);
            makeCombo(orderItems);
            return new Order(orderItems);
        } else if(option == COMBO2) {
            std::vector<Meal> orderItems;
            Hamburguer h;
            orderItems.push_back(h);
            makeCombo(orderItems);
            return new Order(orderItems);
        }
        return nullptr;
    }
}
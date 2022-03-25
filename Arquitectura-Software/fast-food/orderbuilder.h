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

    void makeCombo(std::vector<Meal*>* items) {
        for(Meal* item: *items)
            item->setAsComboItem();
    }

    Order* createIndividualOrder(enum MenuOptions option) {
        std::vector<Meal*>* orderItems = new std::vector<Meal*>;
        if(option == PAPAS_GRANDES) {
            orderItems->push_back(ChipsFactory::createChips(BIG));
        } else if(option == PAPAS_MEDIANAS) {
            orderItems->push_back(ChipsFactory::createChips(MEDIUM));
        } else if(option == PAPAS_CHICAS) {
            orderItems->push_back(ChipsFactory::createChips(SMALL));
        } else if(option == HAMBURGUESA) {
            Hamburguer* hamburguer = new Hamburguer();
            orderItems->push_back(hamburguer);
        } else if(option == NUGGETS) {
            Nugget* nuggets = new Nugget();
            orderItems->push_back(nuggets);
        } else if(option == REFRESCO_GRANDE) {
            orderItems->push_back(SodaFactory::createSoda(BIG));
        } else if(option == REFRESCO_MEDIANO) {
            orderItems->push_back(SodaFactory::createSoda(MEDIUM));
        } else if(option == REFRESCO_CHICO) {
            orderItems->push_back(SodaFactory::createSoda(SMALL));
        }
        Order* newOrder = new Order(orderItems);
        return newOrder;
    }

    Order* createComboOrder(enum MenuOptions option) {
        std::vector<Meal*>* orderItems = new std::vector<Meal*>;
        Meal* food;
        Meal* side;
        Meal* drink;
        if(option == COMBO1) {
            food = new Hamburguer();
            side = ChipsFactory::createChips(BIG);
            drink = SodaFactory::createSoda(BIG);
        } else if(option == COMBO2) {
            food = new Hamburguer();
            side = ChipsFactory::createChips(SMALL);
            drink = SodaFactory::createSoda(SMALL);
        } else if(option == COMBO3) {
            food = new Hamburguer();
            side = ChipsFactory::createChips(MEDIUM);
            drink = SodaFactory::createSoda(MEDIUM);
        } else if(option == COMBO4) {
            food = new Nugget();
            side = ChipsFactory::createChips(BIG);
            drink = SodaFactory::createSoda(BIG);
        } else if(option == COMBO5) {
            food = new Nugget();
            side = ChipsFactory::createChips(SMALL);
            drink = SodaFactory::createSoda(SMALL);
        } else if(option == COMBO6) {
            food = new Nugget();
            side = ChipsFactory::createChips(MEDIUM);
            drink = SodaFactory::createSoda(MEDIUM);
        }
        orderItems->push_back(drink);
        orderItems->push_back(side);
        orderItems->push_back(food);
        makeCombo(orderItems);
        Order* newOrder = new Order(orderItems);
        return newOrder;
    }
}
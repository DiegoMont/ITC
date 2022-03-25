#include "mealsizes.h"
#include "soda.h"
/* Al utilizar esta clase evitamos que el usuario ingrese mal el nombre o el precio del producto. Así siempre se crearan refrescos de un tamaño con un mismo precio */
namespace SodaFactory {
    Soda* createSoda(enum MealSizes size){
        Soda* soda;
        if(size == BIG){
            soda = new Soda("Refresco grande", 20);
        } else if(size == MEDIUM) {
            soda = new Soda("Refresco mediano", 18);
        } else if(size == SMALL) {
            soda = new Soda("Refresco chico", 17);
        }
        return soda;
    }
}

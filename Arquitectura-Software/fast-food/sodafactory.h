#include "mealsizes.h"
#include "soda.h"
/* Al utilizar esta clase evitamos que el usuario ingrese mal el nombre o el precio del producto. Así siempre se crearan refrescos de un tamaño con un mismo precio */
namespace SodaFactory {
    Soda createSoda(enum MealSizes size){
        if(size == BIG){
            Soda c("Refresco grande", 20);
            return c;
        } else if(size == MEDIUM) {
            Soda c("Refresco mediano", 18);
            return c;
        } else if(size == SMALL) {
            Soda c("Refresco chico", 17);
            return c;
        }
    }
}

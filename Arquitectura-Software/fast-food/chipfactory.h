#include "mealsizes.h"
#include "chips.h"

/* Al utilizar esta clase evitamos que el usuario ingrese mal el nombre o el precio del producto. Así siempre se crearan papas de un tamaño con un mismo precio */
namespace ChipsFactory {
    Chips* createChips(enum MealSizes size){
        Chips* c;
        if(size == BIG){
            c = new Chips("Papas grandes", 19);
        } else if(size == MEDIUM) {
            c = new Chips("Papas mediana", 17);
        } else if(size == SMALL) {
            c = new Chips("Papas chicas", 15);
        }
        return c;
    }
}
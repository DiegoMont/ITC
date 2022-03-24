#include "mealsizes.h"
#include "chips.h"

/* Al utilizar esta clase evitamos que el usuario ingrese mal el nombre o el precio del producto. Así siempre se crearan papas de un tamaño con un mismo precio */
namespace ChipsFactory {
    Chips createChips(enum MealSizes size){
        if(size == BIG){
            Chips c("Papas grandes", 19);
            return c;
        } else if(size == MEDIUM) {
            Chips c("Papas mediana", 17);
            return c;
        } else if(size == SMALL) {
            Chips c("Papas chicas", 15);
            return c;
        }
    }
}
#include "Domain.h"
#include <string.h>

char* getAddress(Estate * e) {
    if(e == NULL)
        return NULL;
    return e->address;
}

double getSurface(Estate e) {
    return e.surface;
}

float getPrice(Estate e) {
    return e.price;
}

char* getType(Estate *e) {
    switch (e->type) {
        case House:
            return "House";
        case Apartment:
            return "Apartment";
        case Penthouse:
            return "Penthouse";
    }
    return "Unknown";
}

Estate* createEstate(EstateType type, double surface, int price, char* address) {
    Estate *newEstate = malloc(sizeof(Estate));
    newEstate->type = type;
    newEstate->surface = surface;
    newEstate->price = price;
    strcpy(newEstate->address, address);

    return newEstate;
}

void toString(Estate* e, char str[])
{
    if(e == NULL)
        return;
    sprintf(str, "Estate is %u, surface: %.2f, price: %d, address: %s \n", e->type, e->surface, e->price, e->address);

}

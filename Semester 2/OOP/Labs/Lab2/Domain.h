#include <stdio.h>
#include <stdlib.h>

typedef enum{
    House,
    Apartment,
    Penthouse
}EstateType;

typedef struct{
    EstateType type;
    double surface;
    float price;
    char address[100];
} Estate;


char* getAddress(Estate *e);
double getSurface(Estate e);
float getPrice(Estate e);
char* getType(Estate *e);
Estate* createEstate(EstateType type, double surface, int price, char* address);



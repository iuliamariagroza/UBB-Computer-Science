#include "Domain.h"

typedef struct{
    struct Estate *estates;
    int size;
    int capacity;
}DynamicArray;

DynamicArray* createDynamicArray();
void destroyDynamicArray(DynamicArray *dynamicArray);
void addEstate(DynamicArray* dynamicArray, int type, const char *address, float surface, float price);
void deleteEstate(DynamicArray *dynamicArray, const char *address);
void updateEstate(DynamicArray *dynamicArray, const char *address);
void displayEstates(DynamicArray *dynamicArray, const char *query);
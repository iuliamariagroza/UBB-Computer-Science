#include "DynamicArray.h"
#pragma once

DynamicArray* createDynamicArray()
{
    DynamicArray* dynamicArray = malloc(sizeof(DynamicArray));
    if (dynamicArray == NULL)
        return NULL;
    dynamicArray->size = 0;
    dynamicArray->capacity = 10;
    dynamicArray->estates = malloc(10 * sizeof(Estate));
}

void destroyDynamicArray(DynamicArray* dynamicArray)
{
    if (dynamicArray == NULL)
        return;
    free(dynamicArray->estates);
}

void addEstate(DynamicArray* dynamicArray, int type, const char *address, float surface, float price)
{
    if (dynamicArray->size >= dynamicArray->capacity) {
        dynamicArray->capacity *= 2;
    }
    dynamicArray->estates[dynamicArray->size].type = type;
    strcpy(dynamicArray->estates[dynamicArray->size].address, address);
    dynamicArray->estates[dynamicArray->size].surface = surface;
    dynamicArray->estates[dynamicArray->size].price = price;
    dynamicArray->size++;
}

void deleteEstate(DynamicArray *dynamicArray, const char *address){
    
}

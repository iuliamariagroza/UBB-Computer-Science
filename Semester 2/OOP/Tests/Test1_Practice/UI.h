#pragma once
#include "Service.h"

class UI {
private:
    Service service;
public:
UI(Service initialService);
void printMenu();
void addCar();
void deleteCar();
void updateCar();
void displayAllCars();
void sortCarsByYear();
void countCarsByManufacturer();
void displayAverage();
void displaySortCars();
void start();
};



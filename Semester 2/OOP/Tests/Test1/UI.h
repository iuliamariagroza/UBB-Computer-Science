#pragma once

#include "Service.h"

class UI {
private:
    Service service;
public:
    UI(Service initialService);
    void printMenu();
    void displayAllPatients();
    void addAPatient();
    void displaySortedPatients();
    void start();
};



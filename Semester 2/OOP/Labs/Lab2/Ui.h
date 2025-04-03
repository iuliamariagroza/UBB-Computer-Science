#include "Controller.h"
#include <stdio.h>
#include "Repository.h"

void addNewEstateUi(Repository* estatesRepository);
void deleteEstateUi(Repository* estatesRepository);
void updateEstateUi(Repository* estatesRepository);
void filterEstatesContainingStringAndSortByThePriceUi(Repository* estatesRepository);
void printMenu();
void startApplication();
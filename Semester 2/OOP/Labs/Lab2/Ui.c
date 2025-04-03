#include <stdio.h>
#include "Ui.h"
void printMenu()
{
    printf("Real Estate Management System\n");
    printf("1. Add Estate\n");
    printf("2. Delete Estate\n");
    printf("3. Update Estate\n");
    printf("4. Filter Estates\n");
    printf("5. Exit\n");
}

void startApplication(){
    int optionChosenByTheUser = -1;
    Repository estatesRepository = createRepository();
    printMenu();

    while (optionChosenByTheUser != 0) {
        printf("\nEnter your option: ");
        if (scanf("%d", &optionChosenByTheUser) != 1) {
            optionChosenByTheUser = -1;
            while (getchar() != '\n') {}
            printf("Invalid input. Please enter a valid option.\n");
            continue;
        }
    }

    switch (optionChosenByTheUser) {
        case 1:
            addNewEstateUi(&estatesRepository);
            break;
        case 2:
            deleteEstateUi(&estatesRepository);
            break;
        case 3:
            updateEstateUi(&estatesRepository);
            break;
        case 4:
            filterEstatesContainingStringAndSortByThePriceUi(&estatesRepository);
            break;
        case 0:
            destroyDynamicArray(&estatesRepository.allCountries);
            printf("\nMenu exited successfully.\n");
            break;
        default:
            printf("Invalid option. Please try again.\n");
    }

}
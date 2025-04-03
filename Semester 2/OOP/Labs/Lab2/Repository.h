#include "Domain.h"
#include "DynamicArray.h"

typedef struct{
    DynamicArray allEstates;
}Repository;

Repository createRepository();
void addEstateToRepository(Repository *countriesRepository, Estate countryToBeAdded);
void deleteEstateFromRepository(Repository *estatesRepository, int indexOfTheCountryToBeDeleted);
void filterEstatesContainingStringAndSortByThePriceFromRepository(Repository *estatesRepository, int indexOfTheCountryToBeUpdated, Estate countryWithUpdatedData);
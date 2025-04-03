#include <stdio.h>
#include <string.h>
#include "repository.h"

void initialiseRepositoryWithTenEntries(Repository *estatesRepository)
{
    Estate estate1 = createNewCountry("Belgium", "Europe", 7987467);
    Estate estate2 = createNewCountry("Mexico", "America", 32856753);
    Estate estate3 = createNewCountry("Japan", "Asia", 8752755);
    Estate estate4 = createNewCountry("South Africa", "Africa", 785241);
    Estate estate5 = createNewCountry("Australia", "Australia", 963241);
    Estate estate6 = createNewCountry("Romania", "Europe", 1236549);
    Estate estate7 = createNewCountry("Canada", "America", 3400160);
    Estate estate8 = createNewCountry("Nigeria", "Africa", 8935100);
    Estate estate9 = createNewCountry("Jordan", "Asia", 120000);
    Estate estate10 = createNewCountry("Jordan", "Asia", 120000);
    addEstateToRepository(estatesRepository, estate1);
    addEstateToRepository(estatesRepository, estate2);
    addEstateToRepository(estatesRepository, estate3);
    addEstateToRepository(estatesRepository, estate4);
    addEstateToRepository(estatesRepository, estate5);
    addEstateToRepository(estatesRepository, estate6);
    addEstateToRepository(estatesRepository, estate7);
    addEstateToRepository(estatesRepository, estate8);
    addEstateToRepository(estatesRepository, estate9);
    addEstateToRepository(estatesRepository, estate10);
}

Repository createRepository()
{
    Repository estatesRepository;
    DynamicArray* initialDynamicArray = createDynamicArray(10);
    estatesRepository.allEstates = *initialDynamicArray;
    initialiseRepositoryWithTenEntries(&estatesRepository);
    return estatesRepository;
}

#include <vector>
#include <assert.h>
#include "Tests.h"
#include "Car.h"
#include "Repository.h"
#include "Service.h"

void Tests::testDeleteRepo() {
    std::vector<Car> initialCars = {};
    Repository repo{initialCars};

    Car car1{"Toyota", "Corolla", "Black", 2015};
    Car car2{"Toyota", "Auris", "Blue", 2017};

    repo.addCar(car1);
    repo.addCar(car2);

    assert(repo.getAllCars().size() == 2);

    bool deleted = repo.deleteCar(car1);
    assert(deleted == true);
    assert(repo.getAllCars().size() == 1);

    bool deletedAgain = repo.deleteCar(car1);
    assert(deletedAgain == false);

    Car car3{"Honda", "Civic", "Red", 2018};
    bool deletedNonExistent = repo.deleteCar(car3);
    assert(deletedNonExistent == false);
}

void Tests::testDeleteService() {
    std::vector<Car> initialCars = {};
    Repository repo{initialCars};
    Service service{repo};

    int initialSize = service.getAllCars().size();

    bool deleted = service.deleteCar("Toyota", "Corolla", "Black", 2015);
    assert(deleted == true);
    assert(service.getAllCars().size() == initialSize -1);

    bool deleteAgain = service.deleteCar("Toyota", "Corolla", "Black", 2015);
    assert(deleteAgain == false);

    bool deleteNonexistent = service.deleteCar("Test", "test", "Black", 2015);
    assert(deleteNonexistent == false);
}

void Tests::testAll() {
    testDeleteRepo();
    testDeleteService();
}
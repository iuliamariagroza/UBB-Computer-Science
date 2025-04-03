#pragma once
#include <vector>
#include "Car.h"

class Repository {
private:
    std::vector<Car> allCars;
public:
    Repository(std::vector<Car> initialCars);
    std::vector<Car> getAllCars();
    bool addCar(Car carToAdd);
    bool deleteCar(Car carToDelete);
    bool updateCar(Car carToUpdate, Car updatedCar);
};

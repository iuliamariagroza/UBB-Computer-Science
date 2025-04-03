#include <algorithm>
#include "Repository.h"

Repository::Repository(std::vector<Car> initialCars) {
    this->allCars = initialCars;
}

std::vector<Car> Repository::getAllCars() {
    return this->allCars;
}

bool Repository::addCar(Car carToAdd) {
    auto iterator = std::find(this->allCars.begin(), this->allCars.end(), carToAdd);
    if(iterator != this->allCars.end()){
        return false;
    }
    this->allCars.push_back(carToAdd);
    return true;
}

bool Repository::deleteCar(Car carToDelete) {
    auto iterator = std::find(this->allCars.begin(), this->allCars.end(), carToDelete);
    if(iterator == this->allCars.end()){
        return false;
    }
    this->allCars.erase(iterator);
    return true;
}

bool Repository::updateCar(Car carToUpdate, Car updatedCar) {
    auto iterator = std::find(this->allCars.begin(), this->allCars.end(), carToUpdate);
    if(iterator == this->allCars.end()){
        return false;
    }
    *iterator = updatedCar;
    return true;
}
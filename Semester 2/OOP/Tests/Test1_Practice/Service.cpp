#include <algorithm>
#include "Service.h"

Service::Service(Repository initialRepository): repository{initialRepository} {
    this->addCar("Toyota", "Corolla", "Black", 2015);
    this->addCar("Toyota", "Auris", "Blue", 2017);
    this->addCar("Toyota", "Camry", "White", 2021);
    this->addCar("Toyota", "CHR", "Red", 2011);
}

std::vector<Car> Service::getAllCars() {
    return this->repository.getAllCars();
}

bool Service::addCar(std::string manufacturer_name, std::string model, std::string color, int year_of_fabrication) {
    Car carToAdd{manufacturer_name, model, color, year_of_fabrication};
    return this->repository.addCar(carToAdd);
}

bool Service::deleteCar(std::string manufacturer_name, std::string model, std::string color, int year_of_fabrication) {
    Car carToDelete{manufacturer_name, model, color, year_of_fabrication};
    return this->repository.deleteCar(carToDelete);
}

bool Service::updateCar(std::string manufacturer_name, std::string model, std::string color, int year_of_fabrication,
                        std::string new_manufacturer_name, std::string new_model, std::string new_color,
                        int new_year_of_fabrication) {
    Car carToUpdate{manufacturer_name, model, color, year_of_fabrication};
    Car updatedCar{new_manufacturer_name, new_model, new_color, new_year_of_fabrication};
    return this->repository.updateCar(carToUpdate, updatedCar);
}

void Service::initialiseRepository() {}

int Service::countCarsFromManufacturer(const std::string& manufacturer_name) {
    int totalNumberOfCars = 0;
    for(auto car: getAllCars()){
        if(car.getManufacturer() == manufacturer_name)
        {
            totalNumberOfCars++;
        }
    }
    return totalNumberOfCars;
}
bool sortCars(Car a, Car b){
    return a.getYearOfFabrication() <= b.getYearOfFabrication();
}

std::vector<Car> Service::sortCarsByYear() {
    std::vector<Car> allCars = getAllCars();
    std::sort(allCars.begin(), allCars.end(), sortCars);
    return allCars;
}

double Service::averageYearOfCars() {
    std::vector<Car> allCars = getAllCars();
    int sum = 0;
    for(auto& car: allCars){
        sum+=car.getYearOfFabrication();
    }

    return static_cast<double>(sum) / allCars.size();
}

bool compareCarsByManufacturerAndModel(const Car &a, const Car &b) {
    if (a.getManufacturer() == b.getManufacturer()) {
        return a.getModel() < b.getModel();
    }
    return a.getManufacturer() < b.getManufacturer();
}

std::vector<Car> Service::sortCarsByManufacturerAndModel() {
    // Retrieve a copy of all cars.
    auto cars = repository.getAllCars();

    // Sort the cars using the separate comparator function.
    std::sort(cars.begin(), cars.end(), compareCarsByManufacturerAndModel);

    return cars;
}

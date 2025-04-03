#include "Car.h"

Car::Car(std::string manufacturer_name, std::string model, std::string color, int year_of_fabrication) {
    this->manufacturer_name = manufacturer_name;
    this->model = model;
    this->color = color;
    this->year_of_fabrication = year_of_fabrication;
}

std::string Car::getManufacturer() const {
    return this->manufacturer_name;
}

std::string Car::getModel() const {
    return this->model;
}

std::string Car::getColor() const {
    return this->color;
}

int Car::getYearOfFabrication() const {
    return this->year_of_fabrication;
}

bool Car::operator==(Car carToCompare) {
    if(this->model == carToCompare.getModel() && this->year_of_fabrication == carToCompare.getYearOfFabrication()){
        return true;
    }
    return false;
}
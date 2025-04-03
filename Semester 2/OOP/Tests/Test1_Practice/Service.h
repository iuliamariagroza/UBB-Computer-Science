#pragma once
#include "Repository.h"

class Service {
private:
    Repository repository;
public:
    Service(Repository initialRepository);
    std::vector<Car> getAllCars();
    bool addCar(std::string manufacturer_name, std::string model, std::string color, int year_of_fabrication);
    void initialiseRepository();
    bool deleteCar(std::string manufacturer_name, std::string model, std::string color, int year_of_fabrication);
    bool updateCar(std::string manufacturer_name, std::string model, std::string color, int year_of_fabrication, std::string new_manufacturer_name, std::string new_model, std::string new_color, int new_year_of_fabrication);
    std::vector<Car> sortCarsByYear();
    int countCarsFromManufacturer(const std::string& manufacturer_name);
    double averageYearOfCars();
    std::vector<Car> sortCarsByManufacturerAndModel();
};



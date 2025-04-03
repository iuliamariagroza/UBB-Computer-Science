#include <iostream>
#include "UI.h"


bool isValid(const std::string input){
    for(char c : input){
        if(!std::isalpha(c) || std::isspace(c)){
            return false;
        }
    }
    return true;
}

UI::UI(Service initialService): service{initialService} {
}

void UI::printMenu() {
    std::cout << "Menu\n";
    std::cout << "1. Add Car\n";
    std::cout << "2. Show all cars\n";
    std::cout << "3. Update a car\n";
    std::cout << "4. Delete a car\n";
    std::cout << "5. Sort by year\n";
    std::cout << "6. Count cars by manufacturer\n";
    std::cout << "7. Display year average\n";
    std::cout << "0. EXIT\n";
}

void UI::addCar() {
    std::string  manufacturer_name, model, color;
    int year_of_fabrication = 0;

    std::cout<<"Enter manufacturer: ";
    std::cin.ignore();
    getline(std::cin, manufacturer_name);

    std::cout<<"Enter model: ";
    getline(std::cin, model);

    std::cout<<"Enter color: ";
    getline(std::cin, color);

    std::cout<<"Enter year of fabrication: ";
    std::cin>>year_of_fabrication;

    if(!isValid(manufacturer_name) || !isValid(model) || !isValid(color) || year_of_fabrication < 0){
        std::cout<<"Invalid input. Try again";
    }
    else{
        bool checkAdded = this->service.addCar(manufacturer_name, model, color, year_of_fabrication);
        if(checkAdded == false){
            std::cout<<"Car doesn't exist!";
        }
        else{
            std::cout << "Car added successfully";
        }
    }
}

void UI::deleteCar() {
    std::string  manufacturer_name, model, color;
    int year_of_fabrication = 0;

    std::cout<<"Enter manufacturer: ";
    std::cin.ignore();
    getline(std::cin, manufacturer_name);

    std::cout<<"Enter model: ";
    getline(std::cin, model);

    std::cout<<"Enter color: ";
    getline(std::cin, color);

    std::cout<<"Enter year of fabrication: ";
    std::cin>>year_of_fabrication;

    if(!isValid(manufacturer_name) || !isValid(model) || !isValid(color) || year_of_fabrication < 0){
        std::cout<<"Invalid input. Try again";
    }
    else{
        bool checkDeleted = this->service.deleteCar(manufacturer_name, model, color, year_of_fabrication);
        if(checkDeleted == false){
            std::cout<<"Car doesn't exist!";
        }
        else{
            std::cout << "Car deleted successfully";
        }
    }
}

void UI::updateCar() {
    std::string manufacturer_name, model, color;
    int year_of_fabrication = 0;
    std::cout << "Enter the details of the car you want to update"<<std::endl;
    std::cout << "Enter manufacturer: ";
    std::cin.ignore();
    getline(std::cin, manufacturer_name);

    std::cout << "Enter model: ";
    getline(std::cin, model);

    std::cout << "Enter color: ";
    getline(std::cin, color);

    std::cout << "Enter year of fabrication: ";
    std::cin >> year_of_fabrication;

    if (!isValid(manufacturer_name) || !isValid(model) || !isValid(color) || year_of_fabrication < 0) {
        std::cout << "Invalid input. Try again";
    } else {
        std::string new_manufacturer_name, new_model, new_color;
        int new_year_of_fabrication = 0;

        std::cout << "Enter the new details"<<std::endl;

        std::cout << "New manufacturer: ";
        std::cin.ignore();
        getline(std::cin, new_manufacturer_name);

        std::cout << "New model: ";
        getline(std::cin, new_model);

        std::cout << "New color: ";
        getline(std::cin, new_color);

        std::cout << "New year of fabrication: ";
        std::cin >> new_year_of_fabrication;

        if (!isValid(new_manufacturer_name) || !isValid(new_model) || !isValid(new_color) ||
            new_year_of_fabrication < 0) {
            std::cout << "Invalid input. Try again";
        } else {
            bool updated = this->service.updateCar(manufacturer_name, model, color, year_of_fabrication,
                                                   new_manufacturer_name, new_model, new_color,
                                                   new_year_of_fabrication);
            if (updated) {
                std::cout << "Car updated successfully!" << std::endl;
            } else {
                std::cout << "Car update failed! Car not found or update error." << std::endl;
            }

        }
    }
}

void UI::displayAllCars() {
    std::vector<Car> allCars = this->service.getAllCars();
    for(auto car: allCars){
        std::cout<<"Manufacturer: "<<car.getManufacturer()<<"  "<<"Model: "<<car.getModel()<<"  "<<"Color: "<<car.getColor()<<"  "<<"Year of fabrication: "<<car.getYearOfFabrication()<<std::endl;
    }
}

void UI::sortCarsByYear() {
    std::vector<Car> sortedCars = this->service.sortCarsByYear();
    std::cout<<"Sorted cars:"<<std::endl;

    for(auto const& car: sortedCars)
    {
        std::cout<<"Manufacturer: "<<car.getManufacturer()<<"  "<<"Model: "<<car.getModel()<<"  "<<"Color: "<<car.getColor()<<"  "<<"Year of fabrication: "<<car.getYearOfFabrication()<<std::endl;
    }
}

void UI::countCarsByManufacturer() {
    std::string manufacturer_name;
    std::cout<<"Enter manufacturer: ";
    std::cin.ignore();
    getline(std::cin, manufacturer_name);

    if(!isValid(manufacturer_name)){
        std::cout<<"Invalid input. Try again";
    }
    else{
        int counter = this->service.countCarsFromManufacturer(manufacturer_name);
        std::cout<<"The number of cars from "<<manufacturer_name<< " is: "<<counter;
    }
}

void UI::displayAverage() {
    double average = this->service.averageYearOfCars();
    std::cout<<"The average year of all cars is: "<<average<<std::endl;
}

void UI::displaySortCars() {
    std::vector<Car> sortedCars = this->service.sortCarsByManufacturerAndModel();
    for(auto const& car: sortedCars)
    {
        std::cout<<"Manufacturer: "<<car.getManufacturer()<<"  "<<"Model: "<<car.getModel()<<"  "<<"Color: "<<car.getColor()<<"  "<<"Year of fabrication: "<<car.getYearOfFabrication()<<std::endl;
    }
}

void UI::start() {
    int optionChosen;
    printMenu();
    while(true){
        std::cout << "\nEnter your option: ";
        std::cin >> optionChosen;
        switch (optionChosen) {
            case 1:
                this->addCar();
                break;
            case 2:
                this->displayAllCars();
                break;
            case 3:
                this->updateCar();
                break;
            case 4:
                this->deleteCar();
                break;
            case 5:
                sortCarsByYear();
                break;
            case 6:
                countCarsByManufacturer();
                break;
            case 7:
                displayAverage();
                break;
            case 8:
                displaySortCars();
                break;
            case 0:
                return;
            default:
                std::cout<<"Invalid command. Try again";
                break;
        }

    }
}
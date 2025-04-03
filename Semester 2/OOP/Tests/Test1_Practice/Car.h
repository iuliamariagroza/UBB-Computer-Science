#pragma once
#include <string>

class Car{
private:
    std::string manufacturer_name;
    std::string model;
    std::string color;
    int year_of_fabrication;
public:
    Car(): manufacturer_name(""), model(""), color(""), year_of_fabrication(2000) {}
    Car(std::string manufacturer_name,std::string model,std::string color, int year_of_fabrication);
    std::string getManufacturer() const;
    std::string getModel() const;
    std::string getColor() const;
    int getYearOfFabrication() const;
    bool operator==(Car carToCompare);
};
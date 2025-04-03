#include "Patient.h"

Patient::Patient(std::string name, int age,bool infected, int roomNumber) {
    this->name = name;
    this->age = age;
    this->infected = infected;
    this->roomNumber = roomNumber;
}

std::string Patient::getName() const {
    return this->name;
}

int Patient::getAge() const {
    return this->age;
}

bool Patient::getInfectedStatus() const {
    return this->infected;
}

int Patient::getRoomNumber() const {
    return this->roomNumber;
}

bool Patient::operator==(Patient patientToCompare) {
    if(this->name == patientToCompare.name && this->age==patientToCompare.age && this->infected==patientToCompare.infected && this->roomNumber == patientToCompare.roomNumber){
        return true;
    }
    return false;
}
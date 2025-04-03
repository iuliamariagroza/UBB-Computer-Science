#pragma once
#include <string>

class Patient{
private:
    std::string name;
    int age;
    bool infected;
    int roomNumber;
public:
    Patient(): name(""), age(0), infected(false), roomNumber(0) {}
    Patient(std::string name,int age, bool infected, int roomnNumber);
    std::string getName() const;
    int getAge() const;
    bool getInfectedStatus() const;
    int getRoomNumber() const;
    bool operator==(Patient patientToCompare);
};
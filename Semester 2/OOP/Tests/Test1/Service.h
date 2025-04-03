#pragma once
#include "Repository.h"

class Service {
private:
    Repository repository;
public:
    void initializeRepository();
    Service(Repository initialRepository);
    bool addPatient(std::string name, int age, bool infected, int roomNumber);
    std::vector<Patient> getAllPatients();
    //This function sorts the patients based on their infected status and age
    //Parameters:
      //age: int
    //Return: list with sorted patients
    std::vector<Patient> sortPatients(int age);
};


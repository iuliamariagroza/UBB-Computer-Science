#pragma once
#include <vector>
#include "Patient.h"

class Repository {
private:
    std::vector<Patient> allPatients;
public:
Repository(std::vector<Patient> initialPatients);
//This function adds a new patient to the vector
//Parameters:
  // name: string
  // age: int
  // infected: bool
  // roomNumber: int
//Return: a bool value indicating if the patient was added or not
bool addPatient(Patient patientToAdd);
std::vector<Patient> getAllPatients();
};



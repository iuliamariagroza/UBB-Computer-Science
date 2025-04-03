#include <algorithm>
#include "Repository.h"

Repository::Repository(std::vector<Patient> initialPatients) {
    this->allPatients = initialPatients;
}

bool Repository::addPatient(Patient patientToAdd) {
    auto iterator = std::find(this->allPatients.begin(), this->allPatients.end(), patientToAdd);
    if(iterator  != this->allPatients.end()){
        return false;
    }
    this->allPatients.push_back(patientToAdd);
    return true;
}

std::vector<Patient> Repository::getAllPatients() {
    return this->allPatients;
}
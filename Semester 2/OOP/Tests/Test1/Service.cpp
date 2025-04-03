#include "Service.h"

void Service::initializeRepository() {
}

Service::Service(Repository initialRepository): repository{initialRepository} {
    this->addPatient("Patient 1", 39, false, 323);
    this->addPatient("Patient 2", 39, true, 323);
    this->addPatient("Patient 3", 39, true, 933);
    this->addPatient("Patient 4", 39, false, 943);
    this->addPatient("Patient 5", 39, false, 230);
}

bool Service::addPatient(std::string name, int age, bool infected, int roomNumber) {
    Patient patientToAdd{name, age, infected, roomNumber};
    return this->repository.addPatient(patientToAdd);
}

std::vector<Patient> Service::getAllPatients() {
    return this->repository.getAllPatients();
}

std::vector<Patient> Service::sortPatients(int age) {
    std::vector<Patient> allPatients = getAllPatients();
    std::vector<Patient> sortedPatients = {};
    for(auto patient: allPatients){
        if(patient.getInfectedStatus() == true && patient.getAge() > age){
            sortedPatients.push_back(patient);
        }
    }
    return sortedPatients;
}
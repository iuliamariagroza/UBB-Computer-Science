#include <iostream>
#include "UI.h"

UI::UI(Service initialService): service{initialService} {}

void UI::displayAllPatients() {
    std::vector<Patient> allPatients = this->service.getAllPatients();
    for(const auto& patient: allPatients){
        std::cout<<"Name: "<<patient.getName()<<" Age: "<<patient.getAge()<<" Infected: "<<patient.getInfectedStatus()<<" Room number: "<<patient.getRoomNumber()<<std::endl;
    }
}

void UI::addAPatient() {
    std::string name;
    int age, roomNumber;
    bool infected;

    std::cout<<"Enter name: ";
    std::cin.ignore();
    getline(std::cin, name);

    std::cout<<"Enter age: ";
    std::cin>>age;

    std::cout<<"Enter infected status: ";
    std::cin >> std::boolalpha >> infected;

    std::cout<<"Enter room number: ";
    std::cin>>roomNumber;

    bool checkAdded = this->service.addPatient(name, age, infected, roomNumber);
    if(checkAdded){
        std::cout<<"Patient added successfully";
    }
    else{
        std::cout<<"Patient was not added";
    }
}

void UI::printMenu() {
    std::cout<<"Menu:\n";
    std::cout<<"1. Display all patients\n";
    std::cout<<"2.Add a patient\n";
    std::cout<<"3.Exit\n";
}

void UI::displaySortedPatients() {
    int age;
    std::cout<<"Enter age:";
    std::cin>>age;
    std::vector<Patient> sortedPatients = service.sortPatients(age);
    for(const auto& patient: sortedPatients){
        std::cout<<"Name: "<<patient.getName()<<" Age: "<<patient.getAge()<<" Infected: "<<patient.getInfectedStatus()<<" Room number: "<<patient.getRoomNumber()<<std::endl;
    }
}

void UI::start() {
    int optionChosen;
    printMenu();
    while (true){
        std::cout<<"\nEnter an option: ";
        std::cin>>optionChosen;
        switch (optionChosen) {
            case 1:
                displayAllPatients();
                break;
            case 2:
                addAPatient();
                break;
            case 3:
                return;
            case 4:
                displaySortedPatients();
                break;
            default:
                std::cout<<"Invalid input. Try again!";

        }
    }
}
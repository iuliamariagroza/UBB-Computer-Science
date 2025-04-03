#include <iostream>
#include "UI.h"
#include <cctype>
#include <string>

bool isValidString(const std::string& input) {
    for (char c : input) {
        if (!std::isalpha(c) && !std::isspace(c)) {
            return false;
        }
    }
    return true;
}


UI::UI(Service initialService): service{initialService} {
}

void UI::printMenu() {
    std::cout << "Menu\n\n";
    std::cout << "1. Add Player\n";
    std::cout << "2. Show all Players\n";
    std::cout << "3. Show Players of a team\n";
    std::cout << "4.Delete a player\n";
    std::cout << "0. EXIT\n";
}

void UI::addPlayer() {
    std::string name, nationality, team;
    int numberOfGoals = 0;

    std::cout<<"Name: ";
    std::cin.ignore();
    getline(std::cin, name);


    std::cout<<"Nationality: ";
    std::cin.ignore();
    getline(std::cin, nationality);

    std::cout<<"Team: ";
    std::cin.ignore();
    getline(std::cin, team);

    std::cout << "Goals: ";
    std::cin >> numberOfGoals;

    if(!isValidString(name) || name.empty() || !isValidString(nationality) || nationality.empty() || !isValidString(team) || team.empty() || numberOfGoals < 0){
        std::cout << "Invalid input! Please try again";
    }
    else{
        bool checkAdded = this->service.addPlayer(name, nationality, team, numberOfGoals);
        if(checkAdded == false){
            std::cout<<"Player already exists!";
        }
        else{
            std::cout << "Player added successfully";
        }
    }
}

void UI::displayAllPlayers() {
    std::vector<Player> allPlayers = this->service.getAllPlayers();
    for(const auto& player: allPlayers){
        std::cout<<"Name: "<<player.getName()<<"  "<<"Nationality: "<<player.getNationality()<<"  "<<"Team: "<<player.getTeam()<<"  "<<"Number of goals: "<<player.getNumberOfGoals()<<std::endl;
    }
}

void UI::deletePlayer() {
    std::string name, nationality, team;
    int numberOfGoals = 0;
    std::cout<<"Name: ";
    std::cin.ignore();
    getline(std::cin, name);

    std::cout<<"Nationality: ";
    std::cin.ignore();
    getline(std::cin, nationality);

    std::cout<<"Team: ";
    std::cin.ignore();
    getline(std::cin, team);

    std::cout << "Goals: ";
    std::cin >> numberOfGoals;

    if(!isValidString(name) || name.empty() || !isValidString(nationality) || nationality.empty() || !isValidString(team) || team.empty() || numberOfGoals < 0){
        std::cout << "Invalid input! Please try again";
    }
    else{
        bool checkDeleted = this->service.deletePlayer(name, nationality, team, numberOfGoals);
        if(checkDeleted == false){
            std::cout<<"Player doesn't exist!";
        }
        else{
            std::cout << "Player deleted successfully";
        }
    }
}

void UI::displayAllPlayersOfTeam() {
    std::string team;
    std::cout<<"Enter a team: ";
    std::cin.ignore();
    getline(std::cin, team);
    std::vector<Player> filteredPlayer = this->service.getAllTeam(team);
    for(const auto& player: filteredPlayer){
        std::cout<<"Name: "<<player.getName()<<"  "<<"Nationality: "<<player.getNationality()<<"  "<<"Team: "<<player.getTeam()<<"  "<<"Number of goals: "<<player.getNumberOfGoals()<<std::endl;
    }
    std::cout << "Total Goals: " << this->service.calculateTotalGoalsScoreByATeam(team);
}

void UI::startApplication() {
    int optionChosen;
    printMenu();
    while(true){
        std::cout << "\nEnter your option: ";
        std::cin >> optionChosen;
        if(std::cin.fail() || optionChosen < 0 || optionChosen > 4){
             std::cout<<"Invalid input! Please enter a valid option.\n";
            std::cin.clear();
            continue;
        }
        switch (optionChosen)
        {
           case 1:
               this->addPlayer();
               break;

           case 2:
                this->displayAllPlayers();
                break;
           case 3:
                this->displayAllPlayersOfTeam();
                break;

           case 4:
               this->deletePlayer();
               break;

           case 0:
                return;

           default:
                std::cout << "\nInvalid option! Please try again!\n\n";
                break;
        }
    }
}
#include "Service.h"
#include <vector>
#include <algorithm>

bool sortByGoals(Player first, Player second){
    if(first.getNumberOfGoals() <= second.getNumberOfGoals()){
        return 1;
    }
    return 0;
}

Service::Service(Repository initialRepository): repository{initialRepository} {
    this->addPlayer("Nora Mork", "NOR", "Lavrik", 83);
    this->addPlayer("Issabell", "SWE", "CSM", 3);
    this->addPlayer("Cristina", "ROU", "CSM", 4);
    this->addPlayer("Allison", "FRA", "Lavrik", 5);
    this->addPlayer("Ilina", "RUS", "HCM", 86);
    this->addPlayer("Nerea", "ESP", "Lavrik", 7);
}

std::vector<Player> Service::getAllPlayers() {
    return this->repository.getAllPlayers();
}

bool Service::addPlayer(std::string name, std::string nationality, std::string team, int numberOfGoals) {
    Player playerToAdd{name, nationality, team, numberOfGoals};
    return this->repository.addPlayer(playerToAdd);
}

void Service::sortByNumberOfGoals(std::vector<Player> allPlayers) {
    std::sort(allPlayers.begin(), allPlayers.end(), sortByGoals);
}

void Service::initialiseRepository() {
}

int Service::calculateTotalGoalsScoreByATeam(std::string teamToCalculateTotalGoals) {
    std::vector<Player> allPlayers = this->getAllPlayers();
    int totalGoals = 0;
    for(const auto& player: allPlayers){
        if(player.getTeam() == teamToCalculateTotalGoals){
            totalGoals += player.getNumberOfGoals();
        }
    }
    return totalGoals;
}

bool Service::deletePlayer(std::string name, std::string nationality, std::string team, int numberOfGoals) {
    Player playerToBeDeleted{name, nationality, team, numberOfGoals};
    return this->repository.deletePlayer(playerToBeDeleted);
}

std::vector<Player> Service::getAllTeam(std::string teamToGetPlayers) {
    std::vector<Player> allPlayers = this->repository.getAllPlayers();
    std::vector<Player> filteredPlayers = {};

    for(const auto& player: allPlayers){
        if(player.getTeam() == teamToGetPlayers){
            filteredPlayers.push_back(player);
        }
    }
    std::sort(filteredPlayers.begin(), filteredPlayers.end(), sortByGoals);
    return filteredPlayers;
}
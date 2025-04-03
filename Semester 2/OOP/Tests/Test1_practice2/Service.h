#pragma once
#include "Repository.h"

class Service {
private:
    Repository repository;
public:
    Service(Repository initialRepository);
    std::vector<Player> getAllPlayers();
    bool addPlayer(std::string name, std::string nationality, std::string team, int numberOfGoals);
    void sortByNumberOfGoals(std::vector<Player> allPlayers);
    int calculateTotalGoalsScoreByATeam(std::string teamToCalculateTotalGoals);
    void initialiseRepository();
    std::vector<Player> getAllTeam(std::string teamToGetPlayers);
    bool deletePlayer(std::string name, std::string nationality, std::string team, int numberOfGoals);
};

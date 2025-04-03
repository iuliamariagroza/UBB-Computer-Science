#pragma once
#include "Service.h"

class UI {
private:
    Service service;
public:
    UI(Service initialService);
    void addPlayer();
    void deletePlayer();
    void displayAllPlayers();
    void displayAllPlayersOfTeam();
    void printMenu();
    void startApplication();
};




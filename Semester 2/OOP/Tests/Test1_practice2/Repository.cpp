#include <algorithm>
#include "Repository.h"

Repository::Repository(std::vector<Player> initialPlayers) {
    this->allPlayers = initialPlayers;
}

std::vector<Player> Repository::getAllPlayers() {
    return this->allPlayers;
}

bool Repository::addPlayer(Player playerToAdd) {
    for(const auto& player: this->allPlayers){
        if(player==playerToAdd){
            return false;
        }
    }
    this->allPlayers.push_back(playerToAdd);
    return true;
}

int Repository::getPositionOfPlayer(Player player) {
   const auto iterator = std::find(this->allPlayers.begin(), this->allPlayers.end(), player);
   if(iterator != this->allPlayers.end()){
       return std::distance(this->allPlayers.begin(), iterator);
   }
   return -1;
}

bool Repository::deletePlayer(Player playerToBeDeleted) {
   auto iterator = std::find(this->allPlayers.begin(), this->allPlayers.end(), playerToBeDeleted);
   if(iterator == this->allPlayers.end()){
       return false;
   }
   this->allPlayers.erase(iterator);
   return true;
}
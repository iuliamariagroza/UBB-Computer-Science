#include "Tests.h"
#include "Player.h"
#include "Repository.h"
#include "Service.h"
#include <assert.h>
#include <vector>

void Tests::testAddRepository() {
    std::vector<Player> initialPlayers ={};
    Repository repository{initialPlayers};
    Player validPlayer{"", "", "", 0};

    assert(repository.getAllPlayers().size() == 0);
    repository.addPlayer(validPlayer);
    assert(repository.getAllPlayers().size() == 1);

    bool added = repository.addPlayer(validPlayer);
    assert(added == false);
    assert(repository.getAllPlayers().size() == 1);
}

void Tests::testAddService() {
    std::vector<Player> initialPlayers = {};
    Repository repository{initialPlayers};
    Service service{repository};
    int initialSize = service.getAllPlayers().size();

    service.addPlayer("", "", "", 0);
    assert(service.getAllPlayers().size() == initialSize+1);

    bool added = service.addPlayer("", "", "", 0);
    assert(added == false);
    assert(service.getAllPlayers().size() == initialSize+1);
}

void Tests::testGetAllTeam() {
    std::vector<Player> initialPlayers = {};
    Repository repository{initialPlayers};
    Service service{repository};

    service.addPlayer("Alice", "USA", "TeamA", 5);
    service.addPlayer("Bob", "CAN", "TeamA", 2);
    service.addPlayer("Charlie", "UK", "TeamA", 8);
    service.addPlayer("David", "GER", "TeamB", 10);
    service.addPlayer("Eve", "FRA", "TeamC", 3);

    std::vector<Player> filteredPlayers = service.getAllTeam("TeamA");

    for (const auto& player : filteredPlayers) {
        assert(player.getTeam() == "TeamA");
    }
    assert(filteredPlayers.size() == 3);

    assert(filteredPlayers[0].getNumberOfGoals() == 2);
    assert(filteredPlayers[1].getNumberOfGoals() == 5);
    assert(filteredPlayers[2].getNumberOfGoals() == 8);
}

void Tests::testAll() {
    testAddRepository();
    testAddService();
    testGetAllTeam();
}

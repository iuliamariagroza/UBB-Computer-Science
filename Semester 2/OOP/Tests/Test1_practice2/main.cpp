#include <iostream>
#include <vector>
#include "UI.h"
#include "Tests.h"

int main() {
    std::vector<Player> initialPlayers = {};
    Repository repository{initialPlayers};
    Service service{repository};
    UI ui{service};

    Tests testing;
    testing.testAll();
    std::cout<<"All tests passed\n";
    ui.startApplication();
    return 0;
}



// TIP See CLion help at <a
// href="https://www.jetbrains.com/help/clion/">jetbrains.com/help/clion/</a>.
//  Also, you can try interactive lessons for CLion by selecting
//  'Help | Learn IDE Features' from the main menu.
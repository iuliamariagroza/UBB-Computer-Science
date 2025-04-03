#include <iostream>
#include <vector>
#include "Patient.h"
#include "Repository.h"
#include "Service.h"
#include "UI.h"
#include "Tests.h"

int main() {
    std::vector<Patient> initialPatients = {};
    Repository repository{initialPatients};
    Service service{repository};
    UI ui{service};

    Tests testing;
    testing.testAll();
    std::cout<<"Tests passed";
    ui.start();

    return 0;
}

// TIP See CLion help at <a
// href="https://www.jetbrains.com/help/clion/">jetbrains.com/help/clion/</a>.
//  Also, you can try interactive lessons for CLion by selecting
//  'Help | Learn IDE Features' from the main menu.
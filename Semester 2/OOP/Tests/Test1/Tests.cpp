#include <vector>
#include <assert.h>
#include "Tests.h"
#include "Patient.h"
#include "Repository.h"
#include "Service.h"

void Tests::testAddRepo() {
    std::vector<Patient> initialPatients = {};
    Repository repository{initialPatients};

    assert(repository.getAllPatients().size() == 0);
    Patient validPatient{"name", 32, true, 345};
    bool added = repository.addPatient(validPatient);
    assert(added == true);
    assert(repository.getAllPatients().size() == 1);

    bool addedAgain = repository.addPatient(validPatient);
    assert(addedAgain == false);
}

void Tests::testAddService() {
    std::vector<Patient> initialPatients = {};
    Repository repository{initialPatients};
    Service service{repository};

    int initialSize = service.getAllPatients().size();
    bool added = service.addPatient("name", 23, true, 345);
    assert(added == true);
    assert(service.getAllPatients().size() == initialSize+1);

    bool addedAgain = service.addPatient("name", 23, true, 345);
    assert(addedAgain == false);
}

void Tests::testAll() {
    testAddRepo();
    testAddService();
}
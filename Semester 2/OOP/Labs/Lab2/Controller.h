#ifndef LAB02_CONTROLLER_H
#define LAB02_CONTROLLER_H
#include "Domain.h"

void add_estate_controller(Estate estate);
void delete_estate_controller(const char *address);
void update_estate_controller(const char *address, Estate new_estate);
Estate* find_estate_controller(const char *address);
#endif
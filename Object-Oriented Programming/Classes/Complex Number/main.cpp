#include <iostream>
#include "ComplexNumber.h"

int main() {

    ComplexNumber first(1, 3);
    ComplexNumber second(2, 5);

    first += second;

    std::cout << first << std::endl;

    return 0;
}
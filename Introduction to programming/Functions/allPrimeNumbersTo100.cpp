#include <iostream>
using std::cin;
using std::cout;

bool isPrime(int number) {
    for (int j = 2; j <= number / 2; j++) {
        if (number % j == 0) {
            return false;
        }
    }

    return true;
}

void printAllPrimeNumbersTo100() {
    for (int i = 1; i <= 100; i++) {
        if (isPrime(i)) {
            cout << i << " ";
        }
    }
}



int main() {

    printAllPrimeNumbersTo100();

    return 0;
}
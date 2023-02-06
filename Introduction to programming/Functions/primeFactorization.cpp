#include <iostream>
using namespace std;

bool isPrime(int number) {
    for (int i = 2; i < number / 2; i++) {
        if (number % i == 0) {
            return false;
        }
    }

    return true;
}

void printPrimeMultipliers(int number) {
    int n = 2;

    if (isPrime(number)) {
        cout << number << " is a prime number.";
    }

    while (!isPrime(number)) {
        if (isPrime(n) && number % n == 0) {
            cout << n << " x ";
            number /= n;

            if (isPrime(number)) {
                cout << number;
                break;
            }
        } else {
            n++;
        }
    }
}

int main() {

    int number;

    do {
    cout << "Enter a positive number: ";
    cin >> number;
    } while (number <= 1);

    printPrimeMultipliers(number);

    return 0;
}
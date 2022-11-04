#include <iostream>
using namespace std;

int main() {

    int number;

    cout << "Enter a number: ";
    cin >> number;

    bool isPrime = true;

    if (number == 1) {
        cout << "The number is not prime";
        isPrime = false;
    }

    for (int i = 2; i < number / 2; ++i) {
        if (number % i == 0) {
            cout << "The number is not prime";
            isPrime = false;
            break;
        }
    }

    if (isPrime) {
        cout << "The number is prime";
    }

    return 0;
}
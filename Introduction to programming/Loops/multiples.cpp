#include <iostream>
using namespace std;

int main() {

    // Prints all 3-digit numbers that are multiples of the product of their digits.

    for (int i = 100; i <= 999; i++) {
        int firstDigit = i / 100;
        int secondDigit = (i / 10) % 10;
        int thirdDigit = i % 10;

        int product = firstDigit * secondDigit * thirdDigit;

        if (product != 0 && i % product == 0) {
            cout << i << " ";
        }
    }

    return 0;
}
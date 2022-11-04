#include <iostream>
using namespace std;

int main() {

    // Prints all 3-digit numbers with unique digits

    int counter = 0;

    for (int i = 100; i <= 999; i++) {
        int firstDigit = i / 100;
        int secondDigit = (i / 10) % 10;
        int thirdDigit = i % 10;

        if (firstDigit != secondDigit && firstDigit != thirdDigit && secondDigit != thirdDigit) {
            cout << i << " ";
            counter++;
        }
    } 

    cout << endl << "The count is " << counter;

    return 0;
}
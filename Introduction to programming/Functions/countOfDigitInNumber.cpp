#include <iostream>
using std::cin;
using std::cout;

int countDigit(int number, int digit) {
    int counter = 0;
    while (number > 0) {
        if (number % 10 == digit) {
            counter++;
        }
        number /= 10;
    }
    return counter;
}

bool isDigit(int digit) {
    return digit >= 0 && digit <= 9 ;
}

int main() {

    int number, digit;

    do {
        cout << "Enter positive number and digit: ";
        cin >> number >> digit;
    } while (number < 0 || !isDigit(digit));

    cout << countDigit(number, digit);

    return 0;
}
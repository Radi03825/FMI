#include <iostream>
using namespace std;

double power(double number, int exponent) {
    if (exponent == 0) {
        return 1;
    }

    if (exponent < 0) {
        return 1.0 / power(number, -exponent);
    }

    return number * power(number, exponent - 1);
}

int countDigits(int number) {
    if (number == 0) {
        return 0;
    }

    return 1 + countDigits(number / 10);
}

int reverseNumber(int number) {
    if (number == 0) {
        return 0;
    }

    return number % 10 * power(10, countDigits(number) - 1) + reverseNumber(number / 10);
}

int main() {

    cout << reverseNumber(1234);

    return 0;
}
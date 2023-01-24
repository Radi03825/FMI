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

int main() {

    cout << power(2, 10);

    return 0;
}
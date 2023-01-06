#include <iostream>
using namespace std;

int sumOfDigits(int number) {
    if (number == 0) {
        return 0;
    }

    return number % 10 + sumOfDigits(number / 10);
}

int main() {

    cout << sumOfDigits(123);

    return 0;
}
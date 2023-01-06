#include <iostream>
using namespace std;

int decimalToBinary(int decimalNumber) {
    if (decimalNumber == 0) {
        return 0;
    } else {
        return (decimalNumber % 2 + 10 * decimalToBinary(decimalNumber / 2));
    }
}

int main() {

    cout << decimalToBinary(17);

    return 0;
}
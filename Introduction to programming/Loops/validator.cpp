#include <iostream>
using namespace std;

int main() {

    int number;

    do {
        cout << "Enter 2-digit number: ";
        cin >> number;
    } while (!((number > 9 && number < 100) || (number > -100 && number < -9)));

    return 0;
}
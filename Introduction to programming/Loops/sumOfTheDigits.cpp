#include <iostream>
using namespace std;

int main() {

    int number;

    do {
        cout << "Enter positive number: ";
        cin >> number;
    } while (number < 0);
    
    int sum = 0;

    while (number > 0) {
        sum += number % 10;
        number = number / 10;
    }

    cout << "The sum of the digits is " << sum;

    return 0;
}
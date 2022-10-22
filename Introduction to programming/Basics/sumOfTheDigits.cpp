#include <iostream>
using std::cin;
using std::cout;
using std::endl;

int main() {
    
    int number;

    cout << "Enter 3-digit number: ";
    cin >> number;

    int firstDigit = number / 100;
    int secondDigit = (number / 10) % 10;
    int thirdDigit = number % 10;

    int sum = firstDigit + secondDigit + thirdDigit;

    cout << "First digit: " << firstDigit << endl;
    cout << "Second digit: " << secondDigit << endl;
    cout << "Third digit: " << thirdDigit << endl;

    cout << "Sum of the digits: " << sum;

    return 0;
}
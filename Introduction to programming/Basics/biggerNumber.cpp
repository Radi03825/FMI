#include <iostream>
using std::cin;
using std::cout;

int main() {

    int firstNumber, secondNumber;

    cout << "Enter two numbers: ";
    cin >> firstNumber >> secondNumber;

    // With if statement
    if(firstNumber >= secondNumber) {
      cout << firstNumber;
    } else {
      cout << secondNumber;
    }

    // With ternary operator
    cout << (firstNumber >= secondNumber ? firstNumber : secondNumber);

    return 0;
}
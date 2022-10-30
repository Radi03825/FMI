#include <iostream>
using namespace std;

int main() {

    int firstNumber, secondNumber;

    cout << "Enter two numbers: ";
    cin >> firstNumber >> secondNumber;

    if (firstNumber > 0 && secondNumber > 0) {
      cout << "Both numbers are positive.";
    } else if(firstNumber > 0) {
      cout << "Only first number is positive.";
    } else if (secondNumber > 0) {
      cout << "Only second number is positive.";
    } else {
      cout << "Both numbers are negative.";
    }

    return 0;
}
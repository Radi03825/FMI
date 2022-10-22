#include <iostream>
using std::cin;
using std::cout;

int main() {

    char symbol;
    double firstNumber, secondNumber, result;

    cout << "Enter math symbol(+, -, *, /): ";
    cin >> symbol;

    cout << "Enter first number: ";
    cin >> firstNumber;

    cout << "Enter second number: ";
    cin >> secondNumber;
    
    switch (symbol)
    {
    case '+':
        result = firstNumber + secondNumber;
      break;
    case '-':
        result = firstNumber - secondNumber;
      break;
    case '*':
        result = firstNumber * secondNumber;
      break;
    case '/':
        result = firstNumber / secondNumber;
      break;
    default:
      cout << "Invalid operation";
      return 0;
      break;
    }

    cout << "Result: ";
    cout << firstNumber << " " << symbol << " " << secondNumber << " = " << result;

    return 0;
}
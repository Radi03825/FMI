#include <iostream>
using namespace std;

void swapTwoIntegers(int* firstNumber, int* secondNumber) {     // With Pointers
    int firstValue = *firstNumber;
    *firstNumber = *secondNumber;
    *secondNumber = firstValue;
}

void swapTwoIntegers(int& firstNumber, int& secondNumber) {     // With References
    int firstValue = firstNumber;
    firstNumber = secondNumber;
    secondNumber = firstValue;
}



int main() {

    int firstNumber, secondNumber;

    cout << "First number: ";
    cin >> firstNumber;
    
    cout << "Second number: ";
    cin >> secondNumber;

    swapTwoIntegers(&firstNumber, &secondNumber);   // With Pointers
    
    swapTwoIntegers(firstNumber, secondNumber);     // With References
    
    cout << "First number: " << firstNumber << endl;
    cout << "Second number: " << secondNumber << endl;

    return 0;
}
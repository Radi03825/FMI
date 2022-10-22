#include <iostream>
using std::cin;
using std::cout;
using std::endl;

int main() {
    
    //For 3-digit number.
    int number;

    cout << "Enter 3-digit number: ";
    cin >> number;

    int firstDigit = number / 100;
    int middleDigit = (number / 10) % 10;
    int lastDigit = number % 10;

    cout << "The reverse number of " << number << " is " << lastDigit << middleDigit << firstDigit;


    //For N-digit number.
    int number;

    cout << "Enter number: ";
    cin >> number;

    cout << "The reverse number of " << number << " is ";

    while (number > 0)
    {
        cout << number % 10;
        number /= 10;
    }

    return 0;
}
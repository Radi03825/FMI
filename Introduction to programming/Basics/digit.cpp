#include <iostream>
using namespace std;

int main() {

    int digit;

    cout << "Enter digit: ";
    cin >> digit;

    // Print the number of circles in digit
    switch (digit)
    {
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 7:
        cout << 0;
        break;
    case 0:
    case 6:
    case 9:
        cout << 1;
        break;
    case 8:
        cout << 2;
        break;
    default:
        cout << "Invalid digit." << endl;
        break;
    }

    // Print digit in English
    switch (digit)
    {
    case 0:
        cout << "zero";
        break;
    case 1:
        cout << "one";
        break;
    case 2:
        cout << "two";
        break;
    case 3: 
        cout << "three";
        break;
    case 4:
        cout << "four";
        break;
    case 5:
        cout << "five";
        break;
    case 6:
        cout << "six";
        break;
    case 7:
        cout << "seven";
        break;
    case 8:
        cout << "eight";
        break;
    case 9:
        cout << "nine";
        break;
    default:
        cout << "Invalid digit.";
        break;
    }

    return 0;
}
#include <iostream>
using namespace std;

int main() {

    int year;

    cout << "Enter a year: ";
    cin >> year;

    // With if statement
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
        cout << "Leap year." << endl;
    } else {
        cout << "Not a leap year." << endl;
    }

    // With ternary operator
    bool isLeap = (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));

    cout << (isLeap ? "Leap year." : "Not a leap year.");

    return 0;
}
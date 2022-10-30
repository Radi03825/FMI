#include <iostream>
using namespace std;

int main() {

    int number;

    cout << "Enter 8-digit number: ";
    cin >> number;

    if (number <= 0 || number < 10000000 || number > 99999999) {
        cout << "Invalid date.";
        return 0;
    }

    bool isValidDate = false;

    int year = number % 10000;
    int month = (number / 10000) % 100;
    int day = number / 1000000;

    switch (month)
    {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
        if (day <= 31) {
            isValidDate = true;
        }
        break;
    case 4:
    case 6:
    case 9:
    case 11:
        if (day <= 30) {
            isValidDate = true;
        }
        break;
    case 2:
        // Checking if the year is a leap
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            if (day <= 29) {
                cout << year << " is a leap year." << endl;
                isValidDate = true;
            }
        } else {
            if (day <= 28) {
                isValidDate = true;
            }
        }
        break;
        default:
            cout << "Invalid date.";
            return 0;
    }

    if (isValidDate) {
        if (month < 10) {
            cout << day << "/" << "0" << month << "/" << year << " is valid date.";
        } else {
            cout << day << "/" << month << "/" << year << " is valid date.";
        }
    } else {
        cout << "Invalid date.";
    }

    return 0;
}
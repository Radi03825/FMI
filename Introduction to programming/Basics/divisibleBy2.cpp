#include <iostream>
using std:: cin;
using std:: cout;

int main() {

    int number;

    cout << "Enter number: ";
    cin >> number;

    cout << "Is divisible by 2: ";

    if(number % 2 == 0) {
      cout << "Yes";
    } else {
      cout << "No";
    }

    return 0;
}

#include <iostream>
using namespace std;

int main() {

    int number;

    int sum = 0;

    do {
        cout << "Enter random number: ";
        cin >> number;
        sum += number;
    } while (number != 0);
    

    cout << "The sum of this random numbers is: " << sum;

    return 0;
}
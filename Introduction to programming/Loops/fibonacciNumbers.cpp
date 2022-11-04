#include <iostream>
using namespace std;

int main() {

    int n;

    cout << "Enter a number: ";
    cin >> n;

    int first = 1;
    int second = 2;
    int counter = 0;

    while (n > counter) {
        if (counter == 0) {
            cout << 1 << " ";
        } else if (counter == 1) {
            cout << 2 << " ";
        } else {
            cout << first + second << " ";
            second = first + second;
            first = second - first;
        }

        counter++;
    }

    return 0;
}
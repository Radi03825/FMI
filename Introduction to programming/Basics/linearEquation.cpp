#include <iostream>
using namespace std;

int main() {

    int a, b;

    cout << "A.x + B = 0" << endl;
    cout << "Enter A: ";
    cin >> a;

    cout << "Enter B: ";
    cin >> b;

    if (a == 0) {
        if (b == 0) {
            cout << "Every x is solution.";
        } else {
            cout << "No solutions.";
        }
    } else {
        int x = (-1 * b) / a;
        cout << "Solution: x = " << x;
    }

    return 0;
}
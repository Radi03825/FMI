#include <iostream>
using namespace std;

int main() {

    int a, b, c;
    
    cout << "Enter the sides of the triangle:" << endl;

    cout << " Side 1: ";
    cin >> a;

    cout << " Side 2: ";
    cin >> b;

    cout << " Side 3: ";
    cin >> c;

    if (a + b > c && a + c > b && b + c > a) {
        cout << "Valid triangle.";
    } else {
        cout << "Invalid triangle.";
    }

    return 0;
}
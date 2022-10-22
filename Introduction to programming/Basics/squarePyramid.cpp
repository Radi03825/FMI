#include <iostream>
using std::cin;
using std::cout;
using std::endl;

int main() {
    
    int x, h, k;

    cout << "Enter base edge lenght: ";
    cin >> x;

    cout << "Enter height lenght: ";
    cin >> h;

    cout << "Enter apothem lenght: ";
    cin >> k;

    // S = x * x
    int s = x * x;

    // V = (S * h) / 3
    double v = (s * h) / 3.0;

    // S1 = (P * k) / 2 + S
    int s1 = (4 * x) * k / 2.0 + s;


    cout << "Full area: " << s1 << endl;
    cout << "Volume: " << v;

    return 0;
}
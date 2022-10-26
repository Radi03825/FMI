#include <iostream>
#include <cmath>
using std::cin;
using std::cout;
using std::endl;

int main() {

    double a, b, c;

    cout << "A.x^2 + B.x + C = 0" << endl;
    cout << "Enter A, B and C: ";

    cin >> a >> b >> c;

    if (a == 0) {
        cout << "This is not a quadratic equation.";
    } else {
        double discriminant = (b * b) - (4 * a * c);

        if (discriminant >= 0) {
            double x1 = ((-1 * b) + sqrt(discriminant)) / (2 * a);
            double x2 = ((-1 * b) - sqrt(discriminant)) / (2 * a);

            cout << "x1 = " << x1 << endl;
            cout << "x2 = " << x2 << endl;
        } else {
            cout << "Not real solutions.";
        }
    }

    return 0;
}
#include <iostream>
using namespace std;

int main() {

    // Task 5: Write a Boolean expression that has value of true if the specified condition is true and a value of false if the condition is not true.

    // a) The number is divisible by 4 or by 7.
    int number;

    cout << "Enter number: ";
    cin >> number;

    bool isDivisible = (number % 4 == 0) || (number % 7 == 0);

    cout << boolalpha << isDivisible;


    // b) A.x^2 + B.x + C = 0 doesn't have real roots.
    int a, b, c;

    cout << "A.x^2 + B.x + C = 0" << endl;
    cout << "Enter A, B and C: ";
    cin >> a >> b >> c;

    if (a == 0) {
        cout << "This is not a quadratic equation.";
    } else {
        double discriminant = (b * b) - (4 * a * c);

        cout << boolalpha << (discriminant < 0) ? true : false; 
    }


    // c) Point with coordinates (a, b) lies inside a circle with radius 5 and center (0, 1).
    int a, b;

    cout << "Enter coordinates (a, b): ";
    cin >> a >> b;

    double distance = sqrt((a * a) + ((b - 1) * (b - 1)));
    cout << boolalpha << (distance < 5) ? true : false; 


    // d) Point with coordinates (a, b) lies outside the circle with center (c, d) and radius f.
    int a, b, c, d, f;

    cout << "Enter coordinates (a, b): ";
    cin >> a >> b;

    cout << "Enter coordinates (c, d): ";
    cin >> c >> d;

    cout << "Enter radius: ";
    cin >> f;

    double distance = sqrt(((a - c) * (a - c)) + ((b - d) * (b - d)));

    cout << boolalpha << (distance > f) ? true : false;


    // e) Point belongs to the part of the circle with center (0, 0) and radius 5 in third quadrant.
    int a, b;

    cout << "Enter coordinates (a, b): ";
    cin >> a >> b;

    if (a < 0 && b < 0) {
        double distance = sqrt((a * a) + (b * b));

        cout << boolalpha << (distance <= 5) ? true : false;
    } else {
        cout << boolalpha << false;
    }


    // f) Point belongs to the ring with center (0, 0) and radii 5 and 10.
    int a, b;

    cout << "Enter coordinates (a, b): ";
    cin >> a >> b;

    double distance = sqrt((a * a) + (b * b));

    cout << boolalpha << (distance >= 5 && distance <= 10) ? true : false; 


    // g) X belongs to the interval [0, 1].
    double x;

    cout << "Enter X: ";
    cin >> x;

    cout << boolalpha << (x >= 0 && x <= 1) ? true : false;


    // h) x = max{a, b, c}
    int x, a, b, c;

    cout << "Enter X, A, B and C: ";
    cin >> x >> a >> b >> c;

    bool isEqual;

    if (a >= b && a >= c) {
        isEqual = (a == x); 
    } else if (b >= a && b >= c) {
        isEqual = (b == x); 
    } else {
        isEqual = (c == x); 
    }

    cout << boolalpha << isEqual;


    // i) 
    bool first, second;
    
    cout << "Enter Boolean variables X and Y: ";
    cin >> first >> second;

    // At least one of the Boolean variables x and y has the value true.
    cout << boolalpha << (first || second) ? true : false;

    // The Boolean variables x and y both have the value true.
    cout << boolalpha << (first && second) ? true : false;


    // j) None of the numbers a, b, and c are positive.
    int a, b, c;

    cout << "Enter numbers A, B and C: ";
    cin >> a >> b >> c;

    cout << boolalpha << ((a <= 0) && (b <= 0) && (c <= 0)) ? true : false;


    // k) The digit 7 enters the record of the positive three-digit number.
    int number;

    cout << "Enter 3-digit number: ";
    cin >> number;

    if(number >= 100 && number <= 999) {

        int firstDigit = number / 100;
        int secondDigit = (number / 10) % 10;
        int thirdDigit = number % 10;

        cout << boolalpha << (firstDigit == 7 || secondDigit == 7 || thirdDigit == 7) ? true : false; 
    } else {
        cout << "This is not a 3-digit number.";
    }


    // l) The digits of the three-digit number are different.
    int number;

    cout << "Enter 3-digit number: ";
    cin >> number;

    if (number >= 100 && number <= 999) {
        int firstDigit = number / 100;
        int secondDigit = (number / 10) % 10;
        int thirdDigit = number % 10;

        cout << boolalpha << (firstDigit != secondDigit && firstDigit != thirdDigit && secondDigit != thirdDigit) ? true : false;
    } else {
        cout << "This is not a 3-digit number.";
    }


    // m) At least two of the digits of the three-digit number are equal to each other.
    int number;

    cout << "Enter 3-digit number: ";
    cin >> number;

    if (number >= 100 && number <= 999) {
        int firstDigit = number / 100;
        int secondDigit = (number / 10) % 10;
        int thirdDigit = number % 10;

        cout << boolalpha << (firstDigit == secondDigit || firstDigit == thirdDigit || secondDigit == thirdDigit) ? true : false;

    } else {
        cout << "This is not a 3-digit number.";
    }

    return 0;
}
#include <iostream>
using std::cin;
using std::cout;

int main() {

    //From km/h to mi/h.
    const double MILE_CONVERTER = 0.6213711;

    double kilometers;

    cout << "Enter value in km/h: ";
    cin >> kilometers;

    double miles = kilometers * MILE_CONVERTER;

    cout << "The value in mi/h: " << miles;
    

    //From mi/h to km/h.
    const double KILOMETER_CONVERTER = 1.609344;

    double miles;

    cout << "Enter value on mi/h: ";
    cin >> miles;

    double kilometers = miles * KILOMETER_CONVERTER;

    cout << "The value on km/h: " << kilometers;
}
#include <iostream>
using std::cin;
using std::cout;
using std::endl;
using std::boolalpha;

int abs(int number) {
    return number > 0 ? number : (-1) * number;
}

int pow(int number, int power) {
    int p = 1;
    for (int i = 0; i < power; ++i) {
        p *= number;
    }

    return p;
}

double min(double a, double b) {
    return a > b ? b : a;
}

long max(long a, long b) {
    return a > b ? a : b;
}

int minOfThree(int a, int b, int c) {
    return min(a, min(b, c));
}

long maxOfThree(long a, long b, long c) {
    return max(max(a, b), c);
}

bool isLower(char c) {
    return c >= 'a' && c <= 'z';
}

bool isUpper(char c) {
    return c >= 'A' && c <= 'Z';
}

bool isLetter(char c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
}

bool isDigit(char c) {
    return c >= '0' && c <= '9';
}

char toUpper(char c) {
    if (isLower(c)) {
        return c - ('a' - 'A'); 
    }
    return c;
}

char toLower(char c) {
    if (isUpper(c)) {
        return c + ('a' - 'A');
    }
    return c;
}

int main() {

    cout << abs(-6) << endl; // 6
    cout << pow(3, 3) << endl; // 27
    cout << min(7, 9) << endl; // 7
    cout << max(7, 9) << endl; // 9
    cout << minOfThree(8, 5, 6) << endl; // 5
    cout << maxOfThree(8, 5, 6) << endl; // 8
    cout << boolalpha << isUpper('A') << endl; // true
    cout << boolalpha << isLower('A') << endl; // false
    cout << boolalpha << isLetter('a') << endl; // true
    cout << boolalpha << isDigit('7') << endl; // true
    cout << toUpper('a') << endl; // A
    cout << toLower('A') << endl; // a

    return 0;
}
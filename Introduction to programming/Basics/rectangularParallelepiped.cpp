#include <iostream>
using std::cin;
using std::cout;
using std::endl;

int main() {
    
    int a, b, c;

    cout << "a = ";
    cin >> a;

    cout << "b = ";
    cin >> b;

    cout << "c = ";
    cin >> c;

    int area = 2 * (a * b + a * c + b * c);
    int volume = a * b * c;

    cout << "Surface area = " << area << endl;
    cout << "Volume = " << volume;

    return 0;
}
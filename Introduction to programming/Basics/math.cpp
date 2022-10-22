#include <iostream>
using std::cin;
using std::cout;
using std::endl;

int main() {
    
    int a, b;
    cin >> a >> b;

    int sum = a + b;
    int difference = a - b;
    int product = a * b;

    cout << "Sum: " << sum << " Difference: " << difference << " Product: " << product;

    return 0;
}
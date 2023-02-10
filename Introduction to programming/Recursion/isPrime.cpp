#include <iostream>
using namespace std;

bool isPrime(int number, int i = 2) {
    if (number <= 2) {
        return (number == 2);
    } else if (number % i == 0) {
        return false;
    } else if (i * i > number) {
        return true;
    }

    return isPrime(number, i + 1);
}

int main() {
    
    int number;
    
    cin >> number;

    cout << boolalpha << isPrime(number);

    return 0;
}
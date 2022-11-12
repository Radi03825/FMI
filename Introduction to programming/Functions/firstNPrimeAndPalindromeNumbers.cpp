#include <iostream>
using std::cin;
using std::cout;

bool isPrime(int number) {
    for (int j = 2; j <= number / 2; j++) {
            if (number % j == 0) {
                return false;
            }
    }

    return true;
}

int reverseNumber(int number) {
    int reversedNumber = 0;

    while (number > 0) {
        reversedNumber *= 10;
        reversedNumber += number % 10;

        number /= 10;
    }
    
    return reversedNumber;
}

bool isPalindrome(int number) {
    return number == reverseNumber(number);
}

void printFirstNPrimeAndPalindromeNumbers(int n) {
    for (int i = 1; i <= n; i++) {
        if (isPrime(i) && isPalindrome(i)) {
            cout << i << " ";
        }
    }
}



int main() {

    int n;

    cout << "Enter a number: ";
    cin >> n;

    printFirstNPrimeAndPalindromeNumbers(n);

    return 0;
}
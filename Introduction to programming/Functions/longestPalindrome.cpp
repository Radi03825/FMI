#include <iostream>
using std::cin;
using std::cout;

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

int longestPalindrome(int number) {
    int longestPalindrome = 0;

    while (number > 0) {
        int reversedNumber = reverseNumber(number);

        while (reversedNumber > 0) {
            if (isPalindrome(reversedNumber) && reversedNumber > longestPalindrome) {
                longestPalindrome = reversedNumber;
            }
            reversedNumber /= 10;
        } 

        number /= 10;
    }

    return longestPalindrome;
}



int main() {

    int n;

    cout << "Enter a number: ";
    cin >> n;

    cout << longestPalindrome(n);

    return 0;
}
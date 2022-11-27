#include <iostream>
using std::cin;
using std::cout;

int countSymbols(char arr[256]) {
    int index = 0;

    while (arr[index] != '\0') {
        index++;
    }
    
    return index;
}

bool isPalindrome(char arr[256], int length) {
    for (int i = 0; i < length / 2; i++) {
        if (arr[i] != arr[length - 1 - i]) {
            return false;
        }
    }

    return true;
}



int main() {

    cout << "Enter some text: ";

    char arr[256];

    cin.getline(arr, 256);

    cout << "This is "<< (isPalindrome(arr, countSymbols(arr)) ? "a palindrome." : "not a palindrome.");

    return 0;
}
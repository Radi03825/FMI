#include <iostream>
using namespace std;

bool isPalindrome(char* text, int size) {
    if (size <= 1) {
        return true;
    }

    if (*text == *(text + size - 1)) {
        return isPalindrome(text + 1, size - 2);
    } else {
        return false;
    }
}

int strlen(char* string) {
    if (*string == '\0') {
        return 0;
    } else {
        return (1 + strlen(++string));
    }
}

int main() {

    char string[256];
    
    cin.getline(string, 256);

    cout << boolalpha << isPalindrome(string, strlen(string));

    return 0;
}
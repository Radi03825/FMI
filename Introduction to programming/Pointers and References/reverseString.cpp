#include <iostream>
using namespace std;

int strlen(char* string) {
    if (*string == '\0') {
        return 0;
    } else {
        return (1 + strlen(++string));
    }
}

char* reverse(char* string) {
    int length = strlen(string);

    char* reversedString = new char[length];
    reversedString[length] = '\0';

    int index = length - 1;

    while (*string != '\0') {
        reversedString[index--] = *string;
        string++;
    }

    return reversedString;
}

int main() {

    char string[256];

    cin.getline(string, 256);

    cout << reverse(string);

    return 0;
}
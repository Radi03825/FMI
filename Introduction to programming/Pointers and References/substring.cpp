#include <iostream>
using namespace std;

int strlen(char* string) {
    if (*string == '\0') {
        return 0;
    } else {
        return (1 + strlen(++string));
    }
}

char* substr(char* string, int firstIndex, int lastIndex) {
    int length = strlen(string);

    if (firstIndex > lastIndex || firstIndex < 0 || firstIndex > length - 1 || lastIndex < 0 || lastIndex > length - 1) {
        return nullptr;
    }

    int substringLength = lastIndex - firstIndex + 2;
    char* substring = new char[substringLength];

    int index = firstIndex;
    int indexOfSubstring = 0;

    while (index <= lastIndex) {
        substring[indexOfSubstring++] = string[index++];
    }

    substring[indexOfSubstring] = '\0';

    return substring;
}

int main() {

    char string[256];
    cin.getline(string, 256);

    char* substring = substr(string, 3, 7);

    cout << substring;

    delete[] substring;

    return 0;
}
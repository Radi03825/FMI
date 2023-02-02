#include <iostream>
using namespace std;

void remove(char* string) {
    if (*string == '\0') {
        return;
    } 

    *string = *(string + 1);
    remove(string + 1);
}

void removeDigits(char* string) {
    if (*string == '\0') {
        return;
    }

    if (*string >= '0' && *string <= '9') {
        remove(string);
        removeDigits(string);
    } else {
        removeDigits(string + 1);
    }
}

int main() {

    char input[256];

    cout << "Input: ";
    cin.getline(input, 256);

    removeDigits(input);
    
    cout << "Output: " << input;

    return 0;
}
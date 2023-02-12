#include <iostream>
using namespace std;

bool isBigLetter(char symbol) {
    return (symbol >= 'A' && 'Z' >= symbol);
}

bool isSmallLetter(char symbol) {
    return (symbol >= 'a' && 'z' >= symbol);
}

void swapcase(char* text) {
    if (*text == '\0') {
        return;
    }

    if (isSmallLetter(*text)) {
        *text += 'A' - 'a';
    } else if (isBigLetter(*text)) {
        *text -= 'A' - 'a';
    }

    swapcase(text + 1);
}

int main() {

    char text[] = "Yellow Submarine";

    swapcase(text);

    cout << text;

    return 0;
}
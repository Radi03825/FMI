#include <iostream>
using namespace std;

int strlen(char* string) {
    if (*string == '\0') {
        return 0;
    } else {
        return (1 + strlen(++string));
    }
}

char* replace(char* string, char oldSymbol, char newSymbol) {
    int length = strlen(string);

    while (*string != '\0') {
        if (*string == oldSymbol) {
            *string = newSymbol;
        }

        //*string = (*string == oldSymbol) ? newSymbol : *string;

        string++;
    }

    return string - length;
}

int main() {

    char string[256], oldSymbol, newSymbol;

    cin.getline(string, 256);

    cin >> oldSymbol >> newSymbol;

    cout << replace(string, oldSymbol, newSymbol);

    return 0;
}
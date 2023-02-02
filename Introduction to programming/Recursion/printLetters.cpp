#include <iostream>
using namespace std;

//Print from letter to Z and reversed
void printLetters(char letter) {
    if (letter < 'A' || letter > 'Z') {
        cout << endl;
        return;
    }

    cout << letter << " ";
    printLetters(letter + 1);
    cout << letter << " ";
}

int main() {

    printLetters('A');

    return 0;
}
#include <iostream>
using namespace std;

char* findElement(char* arr, char symbol) {
    for (int i = 0; arr[i]; i++) {
        if (arr[i] == symbol) {
            return &arr[i];
        } 
    }

    return nullptr;
}

int main() {

    char text[] = "abcde";

    cout << *findElement(text, 'b');

    return 0;
}
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

int main() {

    cout << "Enter some text: ";

    char arr[256];

    cin.getline(arr, 256);

    cout << countSymbols(arr);

    return 0;
}
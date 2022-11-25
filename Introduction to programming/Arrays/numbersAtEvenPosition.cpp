#include <iostream>
using std::cin;
using std::cout;

const int MAX_SIZE = 100;

void inputArray(int arr[MAX_SIZE], int n) {
    for (int i = 0; i < n && i < MAX_SIZE; ++i) {
        cout << "Enter a number: ";
        cin >> arr[i];
    }
}

void printNumbersAtEvenPositions(int arr[MAX_SIZE], int n) {
    for (int i = 0; i < n; i++) {
        if (i % 2 == 0) {
            cout << arr[i] << " ";
        }
    }
}



int main() {

    int n;

    cout << "Enter array size: ";
    cin >> n;

    int arr[MAX_SIZE];

    inputArray(arr, n);

    printNumbersAtEvenPositions(arr, n);    

    return 0;
}
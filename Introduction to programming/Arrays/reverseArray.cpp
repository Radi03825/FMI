#include <iostream>
using std::cin;
using std::cout;
using std::endl;

const int MAX_SIZE = 100;

void inputArray(int arr[MAX_SIZE], int n) {
    for (int i = 0; i < n && i < MAX_SIZE; ++i) {
        cout << "Enter a number: ";
        cin >> arr[i];
    }
}

void reverseArray(int arr[MAX_SIZE], int n) {
    for (int i = 0; i < n / 2; i++) {
        int element = arr[i];

        arr[i] = arr[n - 1 - i];
        arr[n - 1 - i] = element;
    }
}

void printArray(int arr[MAX_SIZE], int n) {
    for (int i = 0; i < n && i < MAX_SIZE; i++) {
        cout << arr[i] << " ";
    }
}
 

int main() {

    int n;

    cout << "Enter array size: ";
    cin >> n;

    int arr[MAX_SIZE];

    inputArray(arr, n);

    reverseArray(arr, n);

    printArray(arr, n);

    return 0;
}
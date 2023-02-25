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

void inputArray(double arr[MAX_SIZE], int n) {
    for (int i = 0; i < n && i < MAX_SIZE; ++i) {
        cout << "Enter a number: ";
        cin >> arr[i];
    }
}

void multiplyAllElementsOfArray(int arr[MAX_SIZE], int n, int number) {
    for (int i = 0; i < n && i < MAX_SIZE; i++) {
        arr[i] *= number;
    }
}

void multiplyAllElementsOfArray(double arr[MAX_SIZE], int n, double number) {
    for (int i = 0; i < n && i < MAX_SIZE; i++) {
        arr[i] *= number;
    }
}

void printArray(int arr[MAX_SIZE], int n) {
    for (int i  = 0; i < n && i < MAX_SIZE; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

void printArray(double arr[MAX_SIZE], int n) {
    for (int i  = 0; i < n && i < MAX_SIZE; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}


int main() {

    int n;

    cout << "Enter array size: ";
    cin >> n;

    double doubleArr[MAX_SIZE];
    
    inputArray(doubleArr, n);

    double doubleNumber;

    cout << "Enter double multiply number: ";
    cin >> doubleNumber;

    printArray(doubleArr, n);

    multiplyAllElementsOfArray(doubleArr, n, doubleNumber);

    printArray(doubleArr, n);

    return 0;
}
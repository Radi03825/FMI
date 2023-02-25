#include <iostream>
using namespace std;

double findSum(double* arr, int size) {
    if (size == 0) {
        return 0.0;
    }

    return *arr + findSum(arr + 1, size - 1);
}

double average(double* arr, int length) {
    return findSum(arr, length) / length;
}

void printArray(double* arr, int length) {
    if (length == 0) {
        cout << "Array is empty.";
        return;
    }

    cout << "[";

    for (int i = 0; i < length; i++) {
        cout << arr[i];

        if (i + 1 < length) {
            cout << ", ";
        }
    }

    cout << "]";
}

int main() {

    double arr[] = {7.1, 6.28, 6.6};

    int length = 3;

    printArray(arr, length);

    cout << endl << "Average: " << average(arr, length);

    return 0;
}
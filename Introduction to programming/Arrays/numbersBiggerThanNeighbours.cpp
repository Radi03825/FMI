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

int countNumbersBiggerThanNeighbours (int arr[MAX_SIZE], int n) {
    int counter = 0;

    for (int i = 0; i < n; i++) {
        if (i == 0 && n > 1) {
            if (arr[i] > arr[i + 1]) {
                counter++;
            }
        } else if (i == n - 1 && n > 1) {
            if (arr[i - 1] < arr[i]) {
                counter++;
            }
        } else if (i > 0 && n - i - 1> 0) {
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                counter++;
            }
        }
    }

    return counter;
}



int main() {

    int n;

    cout << "Enter array size: ";
    cin >> n;

    int arr[MAX_SIZE];

    inputArray(arr, n);

    cout << countNumbersBiggerThanNeighbours(arr, n);

    return 0;
}
#include <iostream>
using std::cin;
using std::cout;

const int MAX_SIZE = 100;

void inputArray(int arr[MAX_SIZE], int n) {
    for (int i = 0; i < n && i < MAX_SIZE; i++) {
        cout << "Enter a number: ";
        cin >> arr[i];
    }
}

bool isMirrorArray(int arr[MAX_SIZE], int n) {
    for (int i = 0; i < n / 2; i++) {
        if (arr[i] != arr[n - 1 - i]) {
            return false;
        }
    }

    return true;
}

int main() {

    int arr[MAX_SIZE];

    int n;

    cout << "Enter array size: ";
    cin >> n;

    inputArray(arr, n);

    cout << (isMirrorArray(arr, n) ? "YES" : "NO");

    return 0;
}
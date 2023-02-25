#include <iostream>
using std::cin;
using std::cout;
using std::endl;

const int MAX_SIZE = 1000;

void inputArray(int arr[MAX_SIZE], int n) {
    for (int i = 0; i < n && i < MAX_SIZE; i++) {
        cout << "Enter a number: ";
        cin >> arr[i];
    }
}

bool isInterestingNumber(int number) {
    if (number < 0) {
        return false;
    }

    int previousDigit = number % 10;
    number /= 10;

    while (number > 0) {
        int currentDigit = number % 10;

        if (previousDigit < currentDigit) {
            return false;
        }

        previousDigit = currentDigit;

        number /= 10;
    }

    return true;
}

void printInterestingNumbers(int arr[MAX_SIZE], int n) {
    for (int i = 0; i < n; i++) {
        int currentNumber = arr[i];
        cout << currentNumber << (isInterestingNumber(currentNumber) ? " is" : " is not") << " a interesting number." << endl;
    }
}



int main() {

    int arr[MAX_SIZE];

    int n;

    cout << "Enter array size: ";
    cin >> n;

    inputArray(arr, n);

    printInterestingNumbers(arr, n);

    return 0;
}
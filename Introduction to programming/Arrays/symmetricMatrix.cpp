#include <iostream>
using std::cin;
using std::cout;
using std::endl;

const int MAX_SIZE = 100;

void inputMatrix(int matrix[MAX_SIZE][MAX_SIZE], int n) {
    for (int row = 0; row < n && row < MAX_SIZE; row++) {
        for (int col = 0; col < n && col < MAX_SIZE; col++) {
            cout << "Enter a number: ";
            cin >> matrix[row][col];
        }
    }
}

void printMatrix(int matrix[MAX_SIZE][MAX_SIZE], int n) {
    for (int row = 0; row < n && row < MAX_SIZE; row++) {
        for (int col = 0; col < n && col < MAX_SIZE; col++) {
            cout << matrix[row][col] << " ";
        }

        cout << endl;
    }
}

bool isMatrixSymmetric(int matrix[MAX_SIZE][MAX_SIZE], int n) {
    for (int row = 0; row < n - 1; row++) {
        for (int col = row + 1; col < n; col++) {
            if (matrix[row][col] != matrix[col][row]) {
                return false;
            }
        }
    }

    return true;
}


int main() {

    int matrix[MAX_SIZE][MAX_SIZE];

    int n;
    
    cout << "This is quadration matrix." << endl;
    cout << "Enter N: ";
    cin >> n;

    inputMatrix(matrix, n);
    
    printMatrix(matrix, n);

    cout << "Matrix " << (isMatrixSymmetric(matrix, n) ? "is " : "is not ") << "symmetric.";

    return 0;
}
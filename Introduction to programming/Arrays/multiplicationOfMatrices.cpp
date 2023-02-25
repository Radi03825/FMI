#include <iostream>
using std::cin;
using std::cout;
using std::endl;

const int MAX_SIZE = 100;

void inputMatrix(int matrix[MAX_SIZE][MAX_SIZE], int n, int m) {
    for (int row = 0; row < n && row < MAX_SIZE; row++) {
        for (int col = 0; col < m && col < MAX_SIZE; col++) {
            cout << "Enter a number: ";
            cin >> matrix[row][col];
        }
    }
}

void printMatrix(int matrix[MAX_SIZE][MAX_SIZE], int n, int m) {
    for (int row = 0; row < n && row < MAX_SIZE; row++) {
        for (int col = 0; col < m && col < MAX_SIZE; col++) {
            cout << matrix[row][col] << " ";
        }

        cout << endl;
    }
}

void multiplyMatrices(int result[MAX_SIZE][MAX_SIZE], int firstMatrix[MAX_SIZE][MAX_SIZE], int secondMatrix[MAX_SIZE][MAX_SIZE], int n, int m, int k, int s) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < s; j++) {
            int sum = 0;

            for (int l = 0; l < m; l++) {
                sum += firstMatrix[i][l] * secondMatrix[l][j];
            }

            result[i][j] = sum;
        }
    }
}


int main() {

    int n, m, k, s;

    cout << "Enter rows count: ";
    cin >> n;

    cout << "Enter columns count: ";
    cin >> m;

    int firstMatrix[MAX_SIZE][MAX_SIZE];
    int secondMatrix[MAX_SIZE][MAX_SIZE];

    inputMatrix(firstMatrix, n, m);
    
    cout << "Enter rows count: ";
    cin >> k;

    cout << "Enter columns count: ";
    cin >> s;

    inputMatrix(secondMatrix, k, s);

    if (m == k) {
        int result[MAX_SIZE][MAX_SIZE];
        multiplyMatrices(result, firstMatrix, secondMatrix, n, m, k, s);

        printMatrix(result, n, s);
    } else {
        cout << "These matrices can't be multiplied.";
    }

    return 0;
}
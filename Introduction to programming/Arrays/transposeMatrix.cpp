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

void printTransposedMatrix(int matrix[MAX_SIZE][MAX_SIZE], int n, int m) {
    for (int col = 0; col < m && col < MAX_SIZE; col++) {
        for (int row = 0; row < n && row < MAX_SIZE; row++) {
            cout << matrix[row][col] << " ";
        }

        cout << endl;
    }
}


int main() {

    int matrix[MAX_SIZE][MAX_SIZE];

    int n, m;
    
    cout << "Enter rows count: ";
    cin >> n;

    cout << "Enter columns count: ";
    cin >> m;

    inputMatrix(matrix, n, m);
    
    cout << endl << "Original matrix: " << endl;
    printMatrix(matrix, n, m);

    cout << endl << "Transpose matrix: " << endl;
    printTransposedMatrix(matrix, n, m);

    return 0;
}
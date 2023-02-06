#include <iostream>
using namespace std;

double findMax(double* arr, int size) {
    if (size == 1) {
        return *arr;
    }

    double max = findMax(arr + 1, size - 1);

    return (*arr > max) ? *arr : max;
}

double findMin(double* arr, int size) {
    if (size == 1) {
        return *arr;
    }

    double min = findMin(arr + 1, size - 1);

    return (*arr < min) ? *arr : min;
}



int main() {

    double arr[] = {2, -1.9, 1.1, 3.5, 1, 0, 8.3};

    cout << "Max: " << findMax(arr, 7) << endl;

    cout << "Min: " << findMin(arr, 7) << endl;

    return 0;
}
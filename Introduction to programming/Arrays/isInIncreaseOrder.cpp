#include <iostream>
using namespace std;

bool isInIncreaseOrder(double* arr, int size) {
    if (size == 1) {
        return true;
    }

    if (*arr < *(arr + 1)) {
        return isInIncreaseOrder(arr + 1, size - 1);
    } else {
        return false;
    }
}

int main() {

    double arr[] = {2, -1.9, 1.1, 3.5, 1, 0, 8.3};

    cout << boolalpha << isInIncreaseOrder(arr, 7) << endl;

    return 0;
}
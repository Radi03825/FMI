#include <iostream>
using namespace std;

bool isDegreeOfTwo(int number) {
    if (number < 1) {
        return false;
    }

    while (number > 2) {
        if (number % 2 == 0) {
            number /= 2;
        } else {
            return false;
        }
    }

    return true;
}

int main() {

    cout << boolalpha << isDegreeOfTwo(1024);

    return 0;
}
#include <iostream>
using namespace std;

bool isDegreeOfTwo(int number) {
    if (number == 1) {
        return true;
    }

    if (number > 0 && number % 2 == 0) {
        return isDegreeOfTwo(number / 2);
    } else {
        return false;
    }
}

int main() {

    cout << boolalpha << isDegreeOfTwo(1024);

    return 0;
}
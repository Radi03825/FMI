#include <iostream>
using namespace std;

bool areEquals(char* str1, char* str2) {
    if (*str1 == '\0' && *str2 == '\0') {
        return true;
    } else if (*str1 != *str2) {
        return false;
    } else {
        return areEquals(++str1, ++str2);
    }
}

int main() {

    cout << boolalpha << areEquals("Hello", "Hello");

    return 0;
}
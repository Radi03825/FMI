#include <iostream>
using namespace std;

int recStrlen(const char* string) {
    if (*string == '\0') {
        return 0;
    } else {
        return (1 + recStrlen(++string));
    }
}

int main() {

    cout << recStrlen("Happy new year!");

    return 0;
}
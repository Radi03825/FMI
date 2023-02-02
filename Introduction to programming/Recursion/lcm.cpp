#include <iostream>
using namespace std;

// Find the least common multiple
int lcm(int a, int b) {
    static int common = 0;

    common += a;
    
    if (common % b == 0) {
        return common;
    } else {
        return lcm(a, b);
    }
}

int main() {

    cout << lcm(5, 7);

    return 0;
}
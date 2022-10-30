#include <iostream>
#include <cmath>
using std::cin;
using std::cout;
using std::endl;

int main() {

    int x1, y1, x2, y2;

    cout << "Point 1:" << endl;
    
    cout << "x = ";
    cin >> x1;

    cout << "y = ";
    cin >> y1;

    cout << "Point 2:" << endl;
    
    cout << "x = ";
    cin >> x2;

    cout << "y = ";
    cin >> y2;

    double distance = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

    cout << "Distance: " << distance;

    return 0;
}
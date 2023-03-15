#include <iostream>
#include <cmath>
using std::cin , std::cout, std::endl;

struct Point {
    int x;
    int y;

    double distance(const Point& p) const {
        return sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
    }
};

struct Line {
    Point p1;
    Point p2;

    double length() const {
        return p1.distance(p2);
    }

    bool equal(const Line& l) const {
        return l.length() == length();
    }

    void printInfo() {
        cout << "Point 1 (" << p1.x << ", " << p1.y << "), ";
        cout << "Point 2 (" << p2.x << ", " << p2.y << ") - ";
        cout << p1.distance(p2) << endl;
    }
};

int main() {

    Point p1{0, 0};
    Point p2{1, 1};

    Point p3{1,1};
    Point p4{2,3};

    Line l1{p1,p2};
    Line l2{p3,p4};

    l1.printInfo();
    l2.printInfo();

    return 0;
}
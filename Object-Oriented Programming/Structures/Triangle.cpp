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

struct Triangle {
    Line l1;
    Line l2;
    Line l3;

    bool isTriangle() const {
        return (l1.length() < l2.length() + l3.length() && l2.length() < l1.length() + l3.length() && l3.length() < l1.length() + l2.length());  
    }

    double perimeter() const {
        return l1.length() + l2.length() + l3.length();
    }

    double area() const {
        double p = perimeter() / 2;
        return sqrt(p * (p - l1.length()) * (p - l2.length()) * (p - l3.length()));
    }

    void printInfo() {
        l1.printInfo();
        l2.printInfo();
        l3.printInfo();
        cout << "Perimeter = " << perimeter()  << " " << "Area = " << area() << endl;
    }
};

int main() {

    Point p1{0, 0};
    Point p2{1, 1};

    Point p3{1,1};
    Point p4{2,3};

    Point p5{2,3};
    Point p6{0,0};

    Line l1{p1,p2};
    Line l2{p3,p4};
    Line l3{p5,p6};

    Triangle triangle {l1,l2,l3};

    cout << std::boolalpha << triangle.isTriangle() << endl;

    triangle.printInfo();

    return 0;
}
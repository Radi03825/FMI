#include <iostream>
using std::cin;
using std::cout;
using std::endl;

int main() {
    
    int seconds;

    cout << "Seconds: ";
    cin >> seconds;

    int days = seconds / (60 * 60 * 24);
    int hours = (seconds % (60 * 60 * 24)) / (60 * 60);
    int minutes = ((seconds % (60 * 60 * 24)) % (60 * 60)) / 60;
    seconds = ((seconds % (60 * 60 * 24)) % (60 * 60)) % 60;

    cout << days << " days, " << hours << " hours, " << minutes << " minutes and " << seconds << " seconds";

    return 0;
}
#include <iostream>
#include "Queue.h"

int main() {

    Queue<double> queue;

    queue.push(7.7);
    queue.push(3.7);
    queue.push(5.6);

    std::cout << queue.getSize() << std::endl;
    std::cout << queue.peek() << std::endl;

    queue.pop();

    std::cout << queue.peek() << std::endl;
    std::cout << std::boolalpha << queue.isEmpty() << std::endl;

    

    return 0;
}
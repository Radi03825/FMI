#include <iostream>
#include "Stack.h"

int main() {

    Stack<int> stack;

    stack.push(7);
    
    std::cout << stack.peek() << std::endl;

    std::cout << stack.getSize() << std::endl;

    stack.pop();

    std::cout << std::boolalpha << stack.isEmpty() << std::endl;



    return 0;
}
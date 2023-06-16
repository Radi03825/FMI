#include <iostream>
#include "DoublyLinkedList.h"

using std::cout, std::endl, std::boolalpha;

int main() {

    DoublyLinkedList<int> doublyLinkedList;

    doublyLinkedList.addLast(5);
    doublyLinkedList.addFirst(7);

    cout << doublyLinkedList.getFirst() << endl;
    cout << doublyLinkedList.getLast() << endl;
    cout << doublyLinkedList.getSize() << endl;
    
    cout << doublyLinkedList.removeFirst() << endl;
    cout << doublyLinkedList.removeLast() << endl; 

    cout << boolalpha << doublyLinkedList.isEmpty() << endl;



    return 0;
}
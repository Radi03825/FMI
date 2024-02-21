#include <iostream>
#include "Node.h"

template <typename T>
bool hasCycle(Node<T>* ptr) {
	Node<T>* fast = ptr;
	Node<T>* slow = ptr;

	while (fast && fast->next) {
		fast = fast->next->next;
		slow = slow->next;

		if (fast == slow) {
			return true;
		}
	}

	if (!slow || fast == slow) {
		return false;
	}

	return false;
}

int main() {

	Node<int> f(1);
	Node<int> s(2);
	Node<int> t(3);

	f.next = &s;
	//s.next = &f;
	s.next = &t;
	t.next = &f;

	std::cout << std::boolalpha << hasCycle<int>(&f);

	return 0;
}
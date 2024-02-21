#include <iostream>
#include "Node.h"

template <typename T>
void push(Node<T>*& list, Node<T>* el) {
	if (list) {
		list->next = el;
		list = list->next;
	} else {
		list = el;
	}

	el->next = nullptr;
}

Node<int>* partition(Node<int>* start) {
	Node<int>* odd = nullptr;
	Node<int>* even = nullptr;

	Node<int>* oddStart = nullptr;
	Node<int>* evenStart = nullptr;

	Node<int>* next = nullptr;

	while (start) {
		next = start->next;

		if (start->data % 2) {
			push<int>(odd, start);

			if (!oddStart) {
				oddStart = odd;
			}
		} else {
			push(even, start);

			if (!evenStart) {
				evenStart = even;
			}
		}

		start = next;
	}

	if (!even) {
		return oddStart;
	}

	even->next = oddStart;

	return evenStart;
}

template <typename T>
void print(Node<T>* start) {
	while (start) {
		std::cout << start->data << " ";
		start = start->next;
	}
}

int main() {

	Node<int> first(1);
	Node<int> second(6);
	Node<int> third(7);
	Node<int> fourth(4);
	Node<int> fifth(2);
	Node<int> sixth(9);

	first.next = &second;
	second.next = &third;
	third.next = &fourth;
	fourth.next = &fifth;
	fifth.next = &sixth;

	Node<int>* start = partition(&first);

	/*
	first.next = &third;
	third.next = &sixth;

	Node<int>* start = partition(&first);
	*/

	/*
	second.next = &fourth;
	fourth.next = &fifth;

	Node<int>* start = partition(&second);
	*/

	print<int>(start);

	return 0;
}
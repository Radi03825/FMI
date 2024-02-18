#pragma once
#include "LinkedLists/SinglyLinkedList.h"

template <typename T>
class Queue {

	private:
		SinglyLinkedList<T> data;

	public:
		Queue() = default;
		Queue(const Queue&) = delete;
		Queue& operator=(const Queue&) = delete;
		~Queue() = default;

		void push(const T&);
		void pop();
		const T& front() const;

		size_t size() const;
		bool empty() const;
		void clear();
};

template <typename T>
void Queue<T>::push(const T& element) {
	this->data.push_back(element);
}

template <typename T>
void Queue<T>::pop() {
	this->data.pop_front();
}

template <typename T>
const T& Queue<T>::front() const {
	return this->data.front();
}

template <typename T>
size_t Queue<T>::size() const {
	return this->data.size();
}

template <typename T>
bool Queue<T>::empty() const {
	return this->data.empty();
}

template <typename T>
void Queue<T>::clear() {
	this->data.clear();
}
#pragma once
#include "DynamicArray/DynamicArray.h"

template <typename T>
class Stack {

	private:
		DynamicArray<T> data;

	public:
		Stack() = default;
		Stack(const Stack&) = delete;
		Stack& operator=(const Stack&) = delete;
		~Stack() = default;

		void push(const T&);
		void pop();
		const T& top() const;

		size_t size() const;
		bool empty() const;
		void clear();
};

template <typename T>
void Stack<T>::push(const T& element) {
	this->data.push_back(element);
}

template <typename T>
void Stack<T>::pop() {
	this->data.pop_back();
}

template <typename T>
const T& Stack<T>::top() const {
	return this->data.back();
}

template <typename T>
size_t Stack<T>::size() const {
	return this->data.size();
}

template <typename T>
bool Stack<T>::empty() const {
	return this->data.empty();
}

template <typename T>
void Stack<T>::clear() {
	this->data.clear();
}
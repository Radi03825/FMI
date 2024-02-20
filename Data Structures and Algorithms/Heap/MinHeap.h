#pragma once
#include <vector>

template <typename T>
class MinHeap {

	private:
		std::vector<T> data;

	public:

		const T& top() const;
		void push(const T&);
		void pop();
		int size() const;
		int empty() const;

	private:

		int parent(const int) const;
		int leftChild(const int) const;
		int rightChild(const int) const;
		int minChild(const int) const;
		void siftUp(const int);
		void siftDown(const int);
};

template <typename T>
const T& MinHeap<T>::top() const {
	return data[0];
}

template <typename T>
void MinHeap<T>::push(const T& element) {
	data.push_back(element);
	siftUp(size() - 1);
}

template <typename T>
void MinHeap<T>::pop() {
	std::swap(data[0], data[size() - 1]);
	data.pop_back();
	siftDown(0);
}

template <typename T>
int MinHeap<T>::size() const {
	return this->data.size();
}

template <typename T>
int MinHeap<T>::empty() const {
	return (size() == 0);
}

template <typename T>
int MinHeap<T>::parent(const int index) const {
	return (index - 1) / 2;
}

template <typename T>
int MinHeap<T>::leftChild(const int index) const {
	return 2 * index + 1;
}

template <typename T>
int MinHeap<T>::rightChild(const int index) const {
	return 2 * index + 2;
}

template <typename T>
int MinHeap<T>::minChild(const int index) const {
	if (size() <= leftChild(index)) {
		return -1;
	}

	if (size() <= rightChild(index)) {
		return leftChild(index);
	}

	return (data[leftChild(index)] < data[rightChild(index)]) ? leftChild(index) : rightChild(index);
}

template <typename T>
void MinHeap<T>::siftUp(const int index) {
	if (index <= 0) {
		return;
	}

	if (data[index] < data[parent(index)]) {
		std::swap(data[index], data[parent(index)]);
		siftUp(parent(index));
	}
}

template <typename T>
void MinHeap<T>::siftDown(const int index) {
	int currentMinChild = minChild(index);

	if (currentMinChild == -1) {
		return;
	}

	if (data[currentMinChild] < data[index]) {
		std::swap(data[currentMinChild], data[index]);
		siftDown(currentMinChild);
	}
}
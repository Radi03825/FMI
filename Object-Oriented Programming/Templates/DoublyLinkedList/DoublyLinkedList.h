#pragma once
#include <iostream>
#include <exception>

template <typename T>
class DoublyLinkedList {

	private:
		struct Node {
			T value;
			Node* previous = nullptr;
			Node* next = nullptr;
		};

		int size;
		Node* head;
		Node* tail;

	public:
		DoublyLinkedList();
		DoublyLinkedList(const DoublyLinkedList<T>&);
		DoublyLinkedList(DoublyLinkedList<T>&&);

		DoublyLinkedList<T>& operator=(const DoublyLinkedList<T>&);
		DoublyLinkedList<T>& operator=(DoublyLinkedList<T>&&);

		~DoublyLinkedList();

		void addFirst(const T&);
		void addFirst(T&&);
		
		void addLast(const T&);
		void addLast(T&&);

		T& removeFirst();
		T& removeLast();

		T& getFirst() const;
		T& getLast() const;

		int getSize() const;
		bool isEmpty() const;

	private:
		void copyFrom(const DoublyLinkedList<T>&);
		void moveFrom(DoublyLinkedList<T>&&);
		void free();
		void ensureNotEmpty() const;
};

template <typename T>
DoublyLinkedList<T>::DoublyLinkedList() {
	this->size = 0;
	this->head = nullptr;
	this->tail = nullptr;
}

template <typename T>
DoublyLinkedList<T>::DoublyLinkedList(const DoublyLinkedList<T>& other) {
	copyFrom(other);
}

template <typename T>
DoublyLinkedList<T>::DoublyLinkedList(DoublyLinkedList<T>&& other) {
	moveFrom(std::move(other));
}

template <typename T>
DoublyLinkedList<T>& DoublyLinkedList<T>::operator=(const DoublyLinkedList<T>& other) {
	if (this != &other) {
		free();
		copyFrom(other);
	}

	return *this;
}

template <typename T>
DoublyLinkedList<T>& DoublyLinkedList<T>::operator=(DoublyLinkedList<T>&& other) {
	if (this != &other) {
		free();
		moveFrom(std::move(other));
	}

	return *this;
}

template <typename T>
DoublyLinkedList<T>::~DoublyLinkedList() {
	free();
}

template <typename T>
void DoublyLinkedList<T>::addFirst(const T& obj) {
	Node* newNode = new Node;

	newNode->value = obj;
	newNode->previous = nullptr;
	
	if (isEmpty()) {
		newNode->next = nullptr;

		this->head = newNode;
		this->tail = newNode;
	} else {
		newNode->next = this->head;

		this->head->previous = newNode;
		this->head = newNode;
	}

	this->size++;
}

template <typename T>
void DoublyLinkedList<T>::addFirst(T&& obj) {
	Node* newNode = new Node;

	newNode->value = std::move(obj);
	newNode->previous = nullptr;

	if (isEmpty()) {
		newNode->next = nullptr;

		this->head = newNode;
		this->tail = newNode;
	} else {
		newNode->next = this->head;

		this->head->previous = newNode;
		this->head = newNode;
	}

	this->size++;
}

template <typename T>
void DoublyLinkedList<T>::addLast(const T& obj) {
	Node* newNode = new Node;

	newNode->value = obj;
	newNode->next = nullptr;

	if (isEmpty()) {
		newNode->previous = nullptr;

		this->head = newNode;
		this->tail = newNode;
	} else {
		newNode->previous = this->tail;

		this->tail->next = newNode;
		this->tail = newNode;
	}

	this->size++;
}

template <typename T>
void DoublyLinkedList<T>::addLast(T&& obj) {
	Node* newNode = new Node;

	newNode->value = std::move(obj);
	newNode->next = nullptr;

	if (isEmpty()) {
		newNode->previous = nullptr;

		this->head = newNode;
		this->tail = newNode;
	} else {
		newNode->previous = this->tail;

		this->tail->next = newNode;
		this->tail = newNode;
	}

	this->size++;
}

template <typename T>
T& DoublyLinkedList<T>::removeFirst() {
	ensureNotEmpty();

	this->size--;

	Node* oldHead = this->head;

	this->head = this->head->next;

	if (isEmpty()) {
		this->tail = nullptr;
	}

	T value = oldHead->value;

	T* pointer = &value;

	delete oldHead;

	return *pointer;
}

template <typename T>
T& DoublyLinkedList<T>::removeLast() {
	ensureNotEmpty();

	this->size--;

	Node* oldTail = this->tail;

	this->tail = this->tail->previous;

	if (isEmpty()) {
		this->head = nullptr;
	}

	T value = oldTail->value;

	T* pointer = &value;

	delete oldTail;

	return *pointer;
}

template <typename T>
T& DoublyLinkedList<T>::getFirst() const {
	ensureNotEmpty();

	return this->head->value;
}

template <typename T>
T& DoublyLinkedList<T>::getLast() const {
	ensureNotEmpty();

	return this->tail->value;
}

template <typename T>
int DoublyLinkedList<T>::getSize() const {
	return this->size;
}

template <typename T>
bool DoublyLinkedList<T>::isEmpty() const {
	return this->size == 0;
}

template <typename T>
void DoublyLinkedList<T>::copyFrom(const DoublyLinkedList<T>& other) {
	this->size = other.size;

	Node* current = other.head;

	while (current != nullptr) {
		if (this->head == nullptr) {
			this->head = current;
			this->tail = current;
		} else {
			this->tail->next = current;
			current->previous = this->tail;

			this->tail = current;
		}

		current = current->next;
	}
}

template <typename T>
void DoublyLinkedList<T>::moveFrom(DoublyLinkedList<T>&& other) {
	this->size = other.size;

	Node* current = other.head;

	while (current != nullptr) {

		if (isEmpty()) {
			this->head = current;
		}
		
		this->tail = current;

		current = current->next;
	}

	other.head = nullptr;
	other.tail = nullptr;
	other.size = 0;
}

template <typename T>
void DoublyLinkedList<T>::free() {
	Node* current = this->head;

	while (current != nullptr) {

		Node* next = current->next;

		delete current;

		current = next;
	}

	this->size = 0;
	this->head = nullptr;
	this->tail = nullptr;
}

template <typename T>
void DoublyLinkedList<T>::ensureNotEmpty() const {
	if (isEmpty()) {
		throw std::logic_error("DoublyLinkedList is empty");
	}
}
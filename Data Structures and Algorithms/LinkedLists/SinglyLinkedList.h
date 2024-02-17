#pragma once
#include <stdexcept>

template <typename T>
class SinglyLinkedList {

	private:
		struct Node {
			T data;
			Node* next = nullptr;
		};

		size_t count;
		Node* head;
		Node* tail;

	public:

		SinglyLinkedList();
		SinglyLinkedList(const SinglyLinkedList&);
		SinglyLinkedList& operator=(const SinglyLinkedList&);
		~SinglyLinkedList();

		class Iterator {

			friend class SinglyLinkedList;

	    	private:
		    	Node* current;

		    	Iterator(Node* current) : current(current) {}

	    	public:

		    	//access operators
		    	T& operator*() {
			    	return current->data;
		    	}

		    	const T& operator*() const {
			    	return current->data;
		    	}

		    	T* operator->() {
			    	return &current->data;
		    	}

		    	const T* operator->() const {
			    	return &current->data;
		    	}

		    	//compare operators
		    	bool operator==(const Iterator& other) const {
			    	return (this->current == other.current);
		    	}

		    	bool operator!=(const Iterator& other) const {
			    	return !(*this == other);
		    	}

		    	//modifier operators
		    	Iterator& operator++() {
			    	this->current = this->current->next;

			    	return *this;
		    	}

		    	Iterator operator++(int) {
			    	Iterator res(*this);
			    	++(*this);
			    	return res;
		    	}
		};

		class Const_Iterator {

			friend class SinglyLinkedList;

	    	private:
		    	const Node* current;

		    	Const_Iterator(const Node* current) : current(current) {}

	    	public:

		    	//access operators
		    	const T& operator*() const {
			    	return current->data;
		    	}

		    	const T* operator->() const {
			    	return &current->data;
		    	}

		    	//compare operators
		    	bool operator==(const Const_Iterator& other) const {
			    	return (this->current == other.current);
		    	}

		    	bool operator!=(const Const_Iterator& other) const {
			    	return !(*this == other);
		    	}

		    	//modifier operators
		    	Const_Iterator& operator++() {
			    	this->current = this->current->next;

			    	return *this;
		    	}

		    	Const_Iterator operator++(int) {
			    	Const_Iterator res(*this);
			    	++(*this);
			    	return res;
		    	}
		};

		T& front();
		const T& front() const;

		T& back();
		const T& back() const;

		void push_front(const T&);
		void push_back(const T&);

		void pop_front();
		void pop_back();

		Iterator insert_after(const Iterator&, const T&);
		Iterator remove_after(const Iterator&);
		Iterator find(const T&) const;

		size_t size() const;
		bool empty() const;

		void clear();

		Iterator begin();
		Iterator end();

		Const_Iterator cbegin() const;
		Const_Iterator cend() const;

	private:
		void ensureNonEmpty() const;
		void copyFrom(const SinglyLinkedList&);
		void free();
};

template <typename T>
SinglyLinkedList<T>::SinglyLinkedList() {
	this->count = 0;
	this->head = nullptr;
	this->tail = nullptr;
}

template <typename T>
SinglyLinkedList<T>::SinglyLinkedList(const SinglyLinkedList& other) {
	copyFrom(other);
}

template <typename T>
SinglyLinkedList<T>& SinglyLinkedList<T>::operator=(const SinglyLinkedList& other) {
	if (*this != other) {
		free();
		copyFrom(other);
	}

	return *this;
}

template <typename T>
SinglyLinkedList<T>::~SinglyLinkedList() {
	free();
}

template <typename T>
T& SinglyLinkedList<T>::front() {
	return const_cast<T&>(static_cast<const SinglyLinkedList&>(*this).front());
}

template <typename T>
const T& SinglyLinkedList<T>::front() const {
	ensureNonEmpty();

	return this->head->data;
}

template <typename T>
T& SinglyLinkedList<T>::back() {
	return const_cast<T&>(static_cast<const SinglyLinkedList&>(*this).back());
}

template <typename T>
const T& SinglyLinkedList<T>::back() const {
	ensureNonEmpty();

	return this->tail->data;
}

template <typename T>
void SinglyLinkedList<T>::push_front(const T& element) {
	Node* newNode = new Node{ element };

	if (empty()) {
		this->head = newNode;
		this->tail = newNode;
	}
	else {
		newNode->next = this->head;
		this->head = newNode;
	}

	this->count++;
}

template <typename T>
void SinglyLinkedList<T>::push_back(const T& element) {
	Node* newNode = new Node{ element };

	if (empty()) {
		this->head = newNode;
		this->tail = newNode;
	}
	else {
		this->tail->next = newNode;
		this->tail = newNode;
	}

	this->count++;
}

template <typename T>
void SinglyLinkedList<T>::pop_front() {
	ensureNonEmpty();

	Node* next = this->head->next;

	delete this->head;

	this->count--;

	this->head = next;

	if (empty()) {
		this->tail = nullptr;
	}
}

template <typename T>
void SinglyLinkedList<T>::pop_back() {
	ensureNonEmpty();

	if (size() == 1) {
		delete this->head;

	}
	else {
		Node* prev = this->head;

		while (prev->next != this->tail) {
			prev = prev->next;
		}

		delete this->tail;

		this->tail = prev;
	}

	this->count--;

	if (empty()) {
		this->head = nullptr;
		this->tail = nullptr;
	}
}

template <typename T>
typename SinglyLinkedList<T>::Iterator SinglyLinkedList<T>::insert_after(const Iterator& it, const T& element) {
	Node* newNode = new Node{ element };

	newNode->next = it.current->next;
	it.current->next = newNode;

	this->count++;

	return Iterator(newNode);
}

template <typename T>
typename SinglyLinkedList<T>::Iterator SinglyLinkedList<T>::remove_after(const Iterator& it) {
	if (it.current->next == this->tail) {
		pop_back();

		return end();
	}

	Node* toRemove = it.current->next;

	it.current->next = toRemove->next;

	delete toRemove;

	this->count--;

	return Iterator(it.current->next);
}

template <typename T>
typename SinglyLinkedList<T>::Iterator SinglyLinkedList<T>::find(const T& element) const {
	for (Iterator it = begin(); it != end(); ++it) {
		if ((*it) == element) {
			return it;
		}
	}

	return end();
}

template <typename T>
size_t SinglyLinkedList<T>::size() const {
	return this->count;
}

template <typename T>
bool SinglyLinkedList<T>::empty() const {
	return (size() == 0);
}

template <typename T>
void SinglyLinkedList<T>::clear() {
	free();
}

template <typename T>
typename SinglyLinkedList<T>::Iterator SinglyLinkedList<T>::begin() {
	return Iterator(this->head);
}

template <typename T>
typename SinglyLinkedList<T>::Iterator SinglyLinkedList<T>::end() {
	return Iterator(this->tail->next);
}

template <typename T>
typename SinglyLinkedList<T>::Const_Iterator SinglyLinkedList<T>::cbegin() const {
	return Const_Iterator(this->head);
}

template <typename T>
typename SinglyLinkedList<T>::Const_Iterator SinglyLinkedList<T>::cend() const {
	return Const_Iterator(this->tail->next);
}

template <typename T>
void SinglyLinkedList<T>::ensureNonEmpty() const {
	if (empty()) {
		throw std::logic_error("Empty SinglyLinkedList");
	}
}

template <typename T>
void SinglyLinkedList<T>::copyFrom(const SinglyLinkedList& other) {

	for (Const_Iterator it = other.cbegin(); it != other.cend(); ++it) {
		push_back(*it);
	}
}

template <typename T>
void SinglyLinkedList<T>::free() {

	Node* current = this->head;
	Node* next = nullptr;

	while (current) {

		next = current->next;

		delete current;

		current = next;
	}

	this->head = nullptr;
	this->tail = nullptr;
	this->count = 0;
}

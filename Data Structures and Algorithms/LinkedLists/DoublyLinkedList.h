#pragma once

template <typename T>
class DoublyLinkedList {

	private:
		struct Node {
			T data;
			Node* next = nullptr;
			Node* prev = nullptr;
		};

		int count;
		Node sentinel;

	public:
		DoublyLinkedList();
		DoublyLinkedList(const DoublyLinkedList&);
		DoublyLinkedList& operator=(const DoublyLinkedList&);
		~DoublyLinkedList();

		class Iterator {

			friend class DoublyLinkedList;

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

				Iterator& operator--() {
					this->current = this->current->prev;
					return *this;
				}

				Iterator operator--(int) {
					Iterator res(*this);
					--(*this);
					return res;
				}
		};

		class Const_Iterator {

			friend class DoublyLinkedList;

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

				Const_Iterator& operator--() {
					this->current = this->current->prev;
					return *this;
				}

				Const_Iterator operator--(int) {
					Const_Iterator res(*this);
					--(*this);
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

		Iterator insert(Iterator, const T&);
		Iterator erase(Iterator);
		Iterator merge(DoublyLinkedList& right);
		void splice(Iterator pos, Iterator from, Iterator to);

		bool empty() const;
		int size() const;

		Iterator begin();
		Iterator end();

		Const_Iterator cbegin() const;
		Const_Iterator cend() const;

        void clear();

	private:
		void copyFrom(const DoublyLinkedList&);
		void free();
};

template <typename T>
DoublyLinkedList<T>::DoublyLinkedList() {
	this->count = 0;
	this->sentinel.next = &this->sentinel;
	this->sentinel.prev = &this->sentinel;
}

template <typename T>
DoublyLinkedList<T>::DoublyLinkedList(const DoublyLinkedList& other) : DoublyLinkedList() {
	copyFrom(other);
}

template <typename T>
DoublyLinkedList<T>& DoublyLinkedList<T>::operator=(const DoublyLinkedList& other) {
	if (this != &other) {
		free();
		copyFrom(other);
	}

	return *this;
}

template <typename T>
DoublyLinkedList<T>::~DoublyLinkedList() {
	free();
}

template <typename T>
T& DoublyLinkedList<T>::front() {
	return this->sentinel.next->data;
}

template <typename T>
const T& DoublyLinkedList<T>::front() const {
	return this->sentinel.next->data;
}

template <typename T>
T& DoublyLinkedList<T>::back() {
	return this->sentinel.prev->data;
}

template <typename T>
const T& DoublyLinkedList<T>::back() const {
	return this->sentinel.prev->data;
}

template <typename T>
void DoublyLinkedList<T>::push_front(const T& el) {
	insert(begin(), el);
}

template <typename T>
void DoublyLinkedList<T>::push_back(const T& el) {
	insert(Iterator(&this->sentinel), el);
}

template <typename T>
void DoublyLinkedList<T>::pop_front() {
	erase(begin());
}

template <typename T>
void DoublyLinkedList<T>::pop_back() {
	erase(this->sentinel.prev);
}

template <typename T>
typename DoublyLinkedList<T>::Iterator DoublyLinkedList<T>::insert(Iterator it, const T& el) {
	Node* newNode = new Node{ el };
	newNode->next = it.current;
	newNode->prev = it.current->prev;
	newNode->prev->next = newNode;
	newNode->next->prev = newNode;
	++this->count;

	return Iterator(newNode);
}

template <typename T>
typename DoublyLinkedList<T>::Iterator DoublyLinkedList<T>::erase(Iterator it) {
	it.current->prev->next = it.current->next;
	it.current->next->prev = it.current->prev;

	Iterator result(it.current->next);

	delete it.current;
	--this->count;

	return result;
}

template <typename T>
typename DoublyLinkedList<T>::Iterator DoublyLinkedList<T>::merge(DoublyLinkedList& right) {
	right.sentinel.prev->next = &sentinel;
	right.sentinel.next->prev = &sentinel.prev;

	Iterator result(right.sentinel.next);

	sentinel.prev->next = right.sentinel.next;
	sentinel.prev = right.sentinel.prev;

	this->count += right.count;
	right.count = 0;

	right.sentinel.next = &right.sentinel;
	right.sentinel.prev = &right.sentinel;

	return result;
}

template <typename T>
void DoublyLinkedList<T>::splice(Iterator pos, Iterator from, Iterator to) {
	Node* fromPrev = from.current->prev;
	from.current->prev->next = to.current;
	from.current->prev = pos.current->prev;
	pos.current->prev->next = from.current;
	to.current->prev->next = pos.current;
	pos.current->prev = to.current->prev;
	to.current->prev = fromPrev;

	int counter = 0;
	for (Iterator it = begin(); it != end(); ++it) {
		counter++;
	}

	this->count = counter;
}

template <typename T>
bool DoublyLinkedList<T>::empty() const {
	return (size() == 0);
}

template <typename T>
int DoublyLinkedList<T>::size() const {
	return this->count;
}

template <typename T>
typename DoublyLinkedList<T>::Iterator DoublyLinkedList<T>::begin() {
	return Iterator(this->sentinel.next);
}

template <typename T>
typename DoublyLinkedList<T>::Iterator DoublyLinkedList<T>::end() {
	return Iterator(&this->sentinel);
}

template <typename T>
typename DoublyLinkedList<T>::Const_Iterator DoublyLinkedList<T>::cbegin() const {
	return Const_Iterator(this->sentinel.next);
}

template <typename T>
typename DoublyLinkedList<T>::Const_Iterator DoublyLinkedList<T>::cend() const {
	return Const_Iterator(&this->sentinel);
}

template <typename T>
void DoublyLinkedList<T>::clear() {
    while (!empty()) {
        pop_back();
    }
}

template <typename T>
void DoublyLinkedList<T>::copyFrom(const DoublyLinkedList& other) {
	for (Const_Iterator it = other.cbegin(); it != other.cend(); ++it) {
		push_back(*it);
	}
}

template <typename T>
void DoublyLinkedList<T>::free() {
	while (!empty()) {
		pop_back();
	}
}
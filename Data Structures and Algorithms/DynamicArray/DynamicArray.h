#pragma once
#include <stdexcept>

namespace {
	const int INITIAL_CAPACITY = 4;
}

template <typename T>
class DynamicArray {

	private:
		T* data;
		size_t capacity;
		size_t count;

	public:
		DynamicArray();
		DynamicArray(const DynamicArray&);
		DynamicArray& operator=(const DynamicArray&);

		~DynamicArray();

		class Iterator {
			friend class DynamicArray;

		    private:
			    T* current;

			    explicit Iterator(T* current) : current(current) {}

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
				    ++current;
				    return *this;
			    }

			    Iterator operator++(int) {
				    Iterator prev(*this);

				    ++(*this);

				    return prev;
			    }

			    Iterator& operator--() {
				    --current;
				    return *this;
			    }

			    Iterator operator--(int) {
				    Iterator prev(*this);

				    --(*this);

				    return prev;
			    }
		};

		class Const_Iterator {
			friend class DynamicArray;

		    private:
			    const T* current;

			    explicit Const_Iterator(const T* current) : current(current) {}

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
				    ++current;
				    return *this;
			    }

			    Const_Iterator operator++(int) {
				    Const_Iterator prev(*this);

				    ++(*this);

				    return prev;
			    }

			    Const_Iterator& operator--() {
				    --current;
				    return *this;
			    }

			    Const_Iterator operator--(int) {
				    Const_Iterator prev(*this);

				    --(*this);

				    return prev;
			    }
		};

		T& front();
		const T& front() const;

		T& back();
		const T& back() const;

		void push_back(const T&);
		void pop_back();

		Iterator insert(const Iterator&, const T&);
		Iterator erase(const Iterator&);

		void clear();

		size_t size() const;
		bool empty() const;

		T& operator[](const int);
		const T& operator[](const int) const;

		Iterator begin();
		Iterator end();

		Const_Iterator cbegin() const;
		Const_Iterator cend() const;

	private:
		void ensureNonEmpty() const;
		void copyFrom(const DynamicArray&);
		void free();
		void resize(const size_t);
};

template <typename T>
DynamicArray<T>::DynamicArray() {
	this->count = 0;
	this->capacity = INITIAL_CAPACITY;
	this->data = new T[INITIAL_CAPACITY];
}

template <typename T>
DynamicArray<T>::DynamicArray(const DynamicArray& other) {
	copyFrom(other);
}

template <typename T>
DynamicArray<T>& DynamicArray<T>::operator=(const DynamicArray& other) {
	if (*this != other) {
		free();
		copyFrom(other);
	}

	return *this;
}

template <typename T>
DynamicArray<T>::~DynamicArray() {
	free();
}

template <typename T>
void DynamicArray<T>::push_back(const T& element) {
	if (this->count == this->capacity) {
		resize(this->capacity * 2);
	}

	this->data[this->count++] = element;
}

template <typename T>
void DynamicArray<T>::pop_back() {
	ensureNonEmpty();

	this->count--;

	if (this->count < this->capacity / 2) {
		resize(this->capacity / 2);
	}
}

template <typename T>
typename DynamicArray<T>::Iterator DynamicArray<T>::erase(const Iterator& it) {
	for (Iterator temp = it; temp != end(); ) {
		Iterator prev = temp++;
		*prev = *temp;
	}

	pop_back();

	return it;
}

template <typename T>
typename DynamicArray<T>::Iterator DynamicArray<T>::insert(const Iterator& it, const T& element) {
	if (this->capacity == this->count) {
		resize(this->capacity * 2);
	}

	for (Iterator current = end(); current != it; ) {
		Iterator prev = current--;
		*prev = *current;
	}

	*it = element;

	++this->count;

	return it;
}

template <typename T>
T& DynamicArray<T>::front() {
	return const_cast<T&>(static_cast<const DynamicArray&>(*this).front());
}

template <typename T>
const T& DynamicArray<T>::front() const {
	ensureNonEmpty();

	return this->data[0];
}

template <typename T>
T& DynamicArray<T>::back() {
	return const_cast<T&>(static_cast<const DynamicArray&>(*this).back());
}

template <typename T>
const T& DynamicArray<T>::back() const {
	ensureNonEmpty();

	return this->data[this->count - 1];
}

template <typename T>
void DynamicArray<T>::clear() {
	free();
}

template <typename T>
size_t DynamicArray<T>::size() const {
	return this->count;
}

template <typename T>
bool DynamicArray<T>::empty() const {
	return (size() == 0);
}

template <typename T>
T& DynamicArray<T>::operator[](const int index) {
	if (index < 0 || index >= this->count) {
		throw std::out_of_range("Index out of range");
	}

	return this->data[index];
}

template <typename T>
const T& DynamicArray<T>::operator[](const int index) const {
	if (index < 0 || index >= this->count) {
		throw std::out_of_range("Index out of range");
	}

	return this->data[index];
}

template <typename T>
typename DynamicArray<T>::Iterator DynamicArray<T>::begin() {
	return Iterator(this->data);
}

template <typename T>
typename DynamicArray<T>::Iterator DynamicArray<T>::end() {
	return Iterator(this->data + this->size);
}

template <typename T>
typename DynamicArray<T>::Const_Iterator DynamicArray<T>::cbegin() const {
	return Const_Iterator(this->data);
}

template <typename T>
typename DynamicArray<T>::Const_Iterator DynamicArray<T>::cend() const {
	return Const_Iterator(this->data + this->size);
}

template <typename T>
void DynamicArray<T>::ensureNonEmpty() const {
	if (empty()) {
		throw std::out_of_range("Empty DynamicArray");
	}
}

template <typename T>
void DynamicArray<T>::copyFrom(const DynamicArray& other) {
	this->data = new T[other.capacity];
	this->capacity = other.capacity;
	this->size = other.size;

	for (size_t i = 0; i < this->size; ++i) {
		this->data[i] = other.data[i];
	}
}

template <typename T>
void DynamicArray<T>::free() {
	delete[] this->data;

	this->data = nullptr;
	this->capacity = 0;
	this->count = 0;
}

template <typename T>
void DynamicArray<T>::resize(const size_t capacity) {
	this->capacity = capacity;
	
	if (this->capacity < INITIAL_CAPACITY) {
		this->capacity = INITIAL_CAPACITY;
	}

	T* newData = new T[this->capacity];

	for (size_t i = 0; i < this->count; ++i) {
		newData[i] = this->data[i];
	}

	delete[] this->data;

	this->data = newData;
}

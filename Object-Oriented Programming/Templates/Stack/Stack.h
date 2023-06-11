#pragma once
#include <iostream>
#include <exception>

const int INITIAL_CAPACITY = 4;

template <typename T>
class Stack {

    private:
        T* data;
        size_t capacity;
        size_t size;

    public:
        Stack();
        Stack(const Stack<T>&);
        Stack(Stack<T>&&);

        Stack<T>& operator=(const Stack<T>&);
        Stack<T>& operator=(Stack<T>&&);

        ~Stack();

        void push(const T&);
        void push(T&&);
        void pop();
        const T& peek() const;

        int getSize() const;
        bool isEmpty() const;

    private:
        void copyFrom(const Stack<T>&);
        void moveFrom(Stack<T>&&);
        void free();
        void resize(const int);
};

template <typename T>
Stack<T>::Stack() {
    this->capacity = INITIAL_CAPACITY;
    this->size = 0;
    this->data = new T[this->capacity];
}

template <typename T>
Stack<T>::Stack(const Stack<T>& other) {
    copyFrom(other);
}

template <typename T>
Stack<T>::Stack(Stack<T>&& other) {
    moveFrom(std::move(other));
}

template <typename T>
Stack<T>& Stack<T>::operator=(const Stack<T>& other) {
    if (this != &other) {
        free();
        copyFrom(other);
    }

    return *this;
}

template <typename T>
Stack<T>& Stack<T>::operator=(Stack<T>&& other) {
    if (this != &other) {
        free();
        moveFrom(std::move(other));
    }

    return *this;
}

template <typename T>
Stack<T>::~Stack() {
    free();
}

template <typename T>
void Stack<T>::push(const T& obj) {
    if (this->size == this->capacity) {
        resize(this->capacity * 2);
    }

    this->data[this->size++] = obj;
}

template <typename T>
void Stack<T>::push(T&& obj) {
    if (this->size == this->capacity) {
        resize(this->capacity * 2);
    }

    this->data[this->size++] = std::move(obj);
}

template <typename T>
void Stack<T>::pop() {
    if (isEmpty()) {
        throw std::logic_error("Stack is empty");
    }

    this->size--;

    if (this->size < this->capacity / 2 && this->capacity > INITIAL_CAPACITY) {
        resize(this->capacity / 2);
    }
}

template <typename T>
const T& Stack<T>::peek() const {
    if (isEmpty()) {
        throw std::logic_error("Stack is empty");
    }

    return this->data[this->size - 1];
}

template <typename T>
int Stack<T>::getSize() const {
    return this->size;
}

template <typename T>
bool Stack<T>::isEmpty() const {
    return this->size == 0;
}

template <typename T>
void Stack<T>::copyFrom(const Stack<T>& other) {
    this->capacity = other.capacity;
    this->size = other.size;

    this->data = new T[other.capacity];

    for (int i = 0; i < other.size; i++) {
        this->data[i] = other.data[i];
    }
}

template <typename T>
void Stack<T>::moveFrom(Stack<T>&& other) {
    this->size = other.size;
    this->capacity = other.capacity;

    this->data = other.data;

    other.data = nullptr;
    other.size = 0;
    other.capacity = 0;
}

template <typename T>
void Stack<T>::free() {
    this->capacity = 0;
    this->size = 0;

    delete[] this->data;
}

template <typename T>
void Stack<T>::resize(const int capacity) {
    T* arr = new T[capacity];

    this->capacity = capacity;

    for (int i = 0; i < this->size; i++) {
        arr[i] = this->data[i];
    }

    delete[] this->data;

    this->data = arr;
}

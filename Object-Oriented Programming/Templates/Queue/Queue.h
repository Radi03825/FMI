#pragma once
#include <iostream>
#include <exception>

template <typename T>
class Queue {

    private:
        struct Node {
            T value;
            Node* next = nullptr;
        };

        int size;
        Node* head;
        Node* tail;

    public:
        Queue();
        Queue(const Queue<T>&);
        Queue(Queue<T>&&);

        Queue<T>& operator=(const Queue<T>&);
        Queue<T>& operator=(Queue<T>&&);

        ~Queue();

        void push(const T&);
        void push(T&&);
        void pop();
        const T& peek() const;

        int getSize() const;
        bool isEmpty() const;

    private:
        void copyFrom(const Queue<T>&);
        void moveFrom(Queue<T>&&);
        void free();
};

template <typename T>
Queue<T>::Queue() {
    this->size = 0;
    this->head = nullptr;
    this->tail = nullptr;
}

template <typename T>
Queue<T>::Queue(const Queue<T>& other) {
    copyFrom(other);
}

template <typename T>
Queue<T>::Queue(Queue<T>&& other) {
    moveFrom(std::move(other));
}

template <typename T>
Queue<T>& Queue<T>::operator=(const Queue<T>& other) {
    if (this != &other) {
        free();
        copyFrom(other);
    }

    return *this;
}

template <typename T>
Queue<T>& Queue<T>::operator=(Queue<T>&& other) {
    if (this != &other) {
        free();
        moveFrom(std::move(other));
    }

    return *this;
}

template <typename T>
Queue<T>::~Queue() {
    free();
}

template <typename T>
void Queue<T>::push(const T& obj) {
    Node* newNode = new Node;
    newNode->value = obj;
    newNode->next = nullptr;

    if (this->head == nullptr) {
        this->head = newNode;
    } else {
        this->tail->next = newNode;
    }

    this->tail = newNode;

    this->size++;
}

template <typename T>
void Queue<T>::push(T&& obj) {
    Node* newNode = new Node;
    newNode->value = std::move(obj);
    newNode->next = nullptr;

    if (this->head == nullptr) {
        this->head = newNode;
    } else {
        this->tail->next = newNode;
    }

    this->tail = newNode;

    this->size++;
}

template <typename T>
void Queue<T>::pop() {
    if (isEmpty()) {
        throw std::logic_error("Queue is empty");
    }

    this->size--;

    Node* oldHead = this->head;

    this->head = this->head->next;

    if (isEmpty()) {
        this->tail = nullptr;
    }

    delete oldHead;
}

template <typename T>
const T& Queue<T>::peek() const {
    if (isEmpty()) {
        throw std::logic_error("Queue is empty");
    }

    return this->head->value;
}

template <typename T>
int Queue<T>::getSize() const {
    return this->size;
}

template <typename T>
bool Queue<T>::isEmpty() const {
    return this->size == 0;
}

template <typename T>
void Queue<T>::copyFrom(const Queue<T>& other) {
    this->size = other.size;

    Node* current = other.head;

    while (current != nullptr) {
        if (this->head == nullptr) {
            this->head = current;
        } else {
            this->tail->next = current;
        }

        this->tail = current;

        current = current->next;
    }
}

template <typename T>
void Queue<T>::moveFrom(Queue<T>&& other) {
    this->size = other.size;

    Node* current = other.head;

    while (current != nullptr) {
        if (this->head == nullptr) {
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
void Queue<T>::free() {
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



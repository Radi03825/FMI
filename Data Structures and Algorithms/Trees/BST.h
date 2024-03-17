#pragma once
#include <iostream>
#include <stack>
#include <exception>

template <typename T>
class BST {

	private:

		struct Node {
			T data;
			Node* leftChild;
			Node* rightChild;

			Node(const T data) : data(data), leftChild(nullptr), rightChild(nullptr) {}
		};

		Node* root;
		int count;

		class Iterator {

			friend class BST;

			private:
				std::stack<Node*> stack;
				Node* current = nullptr;

				void pushLeftChildren(Node* node) {
					while (node) {
						stack.push(node);
						node = node->leftChild;
					}
				}

				Iterator(Node* root) {
					if (root) {
						stack.push(nullptr);
						pushLeftChildren(root);
						++(*this);
					}
				}

			public:

				int& operator*() {
					return current->value;
				}

				const int& operator*() const {
					return current->value;
				}

				int* operator->() {
					return &current->value;
				}

				const int* operator->() const {
					return &current->value;
				}

				bool operator==(const Iterator& other) const {
					return (current == other.current);
				}

				bool operator!=(const Iterator& other) const {
					return !(*this == other);
				}

				Iterator& operator++() {
					if (!stack.empty()) {
						current = stack.top();
						stack.pop();
					}

					if (current) {
						pushLeftChildren(current->right);
					}

					return *this;
				}

				Iterator operator++(int) {
					Iterator prev(*this);

					++(*this);

					return prev;
				}
		};

		class Const_Iterator {

			friend class BST;

			private:
				std::stack<const Node*> stack;
				const Node* current = nullptr;

				void pushLeftChildren(Node* node) {
					while (node) {
						stack.push(node);
						node = node->leftChild;
					}
				}

				Const_Iterator(Node* root) {
					if (root) {
						stack.push(nullptr);
						pushLeftChildren(root);
						++(*this);
					}
				}

			public:

				const int& operator*() const {
					return current->value;
				}

				const int* operator->() const {
					return &current->value;
				}

				bool operator==(const Const_Iterator& other) const {
					return (current == other.current);
				}

				bool operator!=(const Const_Iterator& other) const {
					return !(*this == other);
				}

				Const_Iterator& operator++() {
					if (!stack.empty()) {
						current = stack.top();
						stack.pop();
					}

					if (current) {
						pushLeftChildren(current->right);
					}

					return *this;
				}

				Const_Iterator operator++(int) {
					Const_Iterator prev(*this);

					++(*this);

					return prev;
				}
		};

	public:
		BST();
		BST(const BST&);
		BST& operator=(const BST&);
		~BST();

		void insert(const T&);
		void remove(const T&);
		bool contains(const T&) const;
		int size() const;
		int height() const;
		void clear();

		void printPreOrder() const;
		void printInOrder() const;
		void printPostOrder() const;

		Iterator begin();
		Iterator end();

		Const_Iterator cbegin() const;
		Const_Iterator cend() const;

	private:
		void insert(Node*&, const T&);
		void remove(Node*&, const T&);
		Node* extractMin(Node*&);
		int height(const Node*) const;
		bool contains(const Node*, const T&) const;
		void printPreOrderHelper(const Node*) const;
		void printInOrderHelper(const Node*) const;
		void printPostOrderHelper(const Node*) const;
		void copy(const Node*);
		void clear(const Node*);
		void copyFrom(const BST&);
		void free();
};

template <typename T>
BST<T>::BST() {
	this->root = nullptr;
	this->count = 0;
}

template <typename T>
BST<T>::BST(const BST& other) {
	copyFrom(other);
}

template <typename T>
BST<T>& BST<T>::operator=(const BST& other) {
	if (this != &other) {
		free();
		copyFrom(other);
	}

	return *this;
}

template <typename T>
BST<T>::~BST() {
	free();
}

template <typename T>
void BST<T>::insert(const T& element) {
	insert(this->root, element);
	this->count++;
}

template <typename T>
void BST<T>::remove(const T& element) {
	remove(this->root, element);
}

template <typename T>
bool BST<T>::contains(const T& element) const {
	return contains(this->root, element);
}

template <typename T>
int BST<T>::size() const {
	return this->count;
}

template <typename T>
int BST<T>::height() const {
	return height(this.root);
}

template <typename T>
void BST<T>::clear() {
	free();
}

template <typename T>
void BST<T>::printPreOrder() const {
	printPreOrderHelper(this->root);
}

template <typename T>
void BST<T>::printInOrder() const {
	printInOrderHelper(this->root);
}

template <typename T>
void BST<T>::printPostOrder() const {
	printPostOrderHelper(this->root);
}
template <typename T>
typename BST<T>::Iterator BST<T>::begin() {
	return Iterator(this->root);
}

template <typename T>
typename BST<T>::Iterator BST<T>::end() {
	return Iterator(nullptr);
}

template <typename T>
typename BST<T>::Const_Iterator BST<T>::cbegin() const {
	return Const_Iterator(this->root);
}

template <typename T>
typename BST<T>::Const_Iterator BST<T>::cend() const {
	return Const_Iterator(nullptr);
}

template <typename T>
void BST<T>::insert(Node*& node, const T& element) {
	if (!node) {
		node = new Node(element);
	} else {
		insert((element > node->data) ? node->rightChild : node->leftChild , element);
	}
}

template <typename T>
void BST<T>::remove(Node*& node, const T& element) {
	if (!node) {
		return;
	}

	if (node->data == element) {
		Node* toDelete = node;

		if (!root->leftChild) {
			node = node->rightChild;
		} else if (!node->rightChild) {
			node = node->leftChild;
		} else {
			Node* rightMin = extractMin(node->rightChild);
			rightMin->leftChild = node->leftChild;
			rightMin->rightChild = node->rightChild;

			node = rightMin;
		}

		this->count--;
		delete toDelete;

	} else {
		remove((element > node->data) ? node->rightChild : node->leftChild, element);
	}
}

template <typename T>
typename BST<T>::Node* BST<T>::extractMin(Node*& node) {
	if (node->leftChild) {
		return extractMin(node->leftChild);
	}

	Node* current = node;
	node = node->rightChild;

	return current;
}

template <typename T>
int BST<T>::height(const Node* node) const {
	if (!node) {
		return 0;
	}

	return std::max(height(node->leftChild), height(node->rightChild)) + 1;
}

template <typename T>
bool BST<T>::contains(const Node* node, const T& element) const {
	if (!node) {
		return false;
	} else if (node->data == element) {
		return true;
	}

	return (node->data > element) ? contains(node->leftChild, element) : contains(node->rightChild, element);
}

template <typename T>
void BST<T>::printPreOrderHelper(const Node* node) const {
	if (!node) {
		return;
	}

	std::cout << node->data << " ";

	printPreOrderHelper(node->leftChild);
	printPreOrderHelper(node->rightChild);
}

template <typename T>
void BST<T>::printInOrderHelper(const Node* node) const {
	if (!node) {
		return;
	}

	printInOrderHelper(node->leftChild);

	std::cout << node->data << " ";

	printInOrderHelper(node->rightChild);
}

template <typename T>
void BST<T>::printPostOrderHelper(const Node* node) const {
	if (!node) {
		return;
	}

	printPostOrderHelper(node->leftChild);
	printPostOrderHelper(node->rightChild);

	std::cout << node->data << " ";
}

template <typename T>
void BST<T>::copy(const Node* node) {
	if (!node) {
		return node;
	}

	Node* newNode = nullptr;

	try {
		newNode = new Node(node->data, copy(node->leftChild), copy(node->rightChild));
	} catch (const std::bad_alloc& e) {
		delete newNode;

		throw e;
	}

	return newNode;
}

template <typename T>
void BST<T>::clear(const Node* node) {
	if (node) {
		clear(node->leftChild);
		clear(node->rightChild);

		delete node;
	}
}

template <typename T>
void BST<T>::copyFrom(const BST& other) {
	this->root = copy(other.root);
	this->count = other.count;
}

template <typename T>
void BST<T>::free() {
	clear(this->root);
	this->count = 0;
}
#include <iostream>
#include <fstream>
#include <exception>

struct DLList {
	int length = 0;

	struct DLNode {
		int data;
		DLNode* next = nullptr;
		DLNode* prev = nullptr;

		DLNode(const int data) : data(data) {}

	} *first, * last;

	DLList() : first(nullptr), last(nullptr) {}
	DLList(const int length) : length(length), first(nullptr), last(nullptr) {}
};

void clear(DLList* list) {
	if (!list->first) {
		return;
	}

	DLList::DLNode* root = list->first;
	DLList::DLNode* next = root->next;

	while (next) {
		delete root;
		root = next;
		next = next->next;
	}

	delete root;
	list->length = 0;

	list->first = nullptr;
	list->last = nullptr;
}

DLList* read(int length, std::istream& input) {
	DLList::DLNode* root = nullptr;
	DLList::DLNode* tail = nullptr;

	DLList* list = new DLList();
	list->length = length;

	for (int i = 0; i < length; i++) {
		try {
			int number;

			input >> number;

			DLList::DLNode* newNode = new DLList::DLNode(number);

			if (!root) {
				root = newNode;
				tail = newNode;

				list->first = root;
			}
			else {
				tail->next = newNode;

				if (i + 1 != length) {
					tail = tail->next;
				}
			}
		}
		catch (const std::bad_alloc& e) {
			clear(list);

			throw e;
		}
	}

	list->last = tail;

	return list;
}

bool same(const DLList* first, const DLList* second) {
	DLList::DLNode* firstRoot = first->first;
	DLList::DLNode* secondRoot = second->first;

	while (firstRoot && secondRoot) {
		if (firstRoot->data != secondRoot->data) {
			return false;
		}

		firstRoot = firstRoot->next;
		secondRoot = secondRoot->next;
	}

	if (firstRoot || secondRoot) {
		return false;
	}

	return true;
}

void reverse(DLList* list) {

	if (!list->first) {
		return;
	}

	DLList::DLNode* first = list->first;

	DLList::DLNode* prev = nullptr;
	DLList::DLNode* current = list->first;
	DLList::DLNode* next = nullptr;

	while (current) {
		next = current->next;
		current->next = prev;
		current->prev = next;
		prev = current;

		current = next;
	}

	list->first = list->last;
	list->last = first;
}

DLList* range(const DLList* list, int from, int to) {
	DLList::DLNode* root = list->first;

	DLList* result = new DLList();

	DLList::DLNode* current = nullptr;
	DLList::DLNode* newNode = nullptr;

	int index = 0;
	while (root) {
		if (index >= to) {
			break;
		}

		if (index >= from && index < to) {
			try {
				newNode = new DLList::DLNode(root->data);

				if (result->length == 0) {
					result->first = newNode;
					current = newNode;
				}
				else {
					current->next = newNode;
				}

				current = current->next;
				result->length++;

				if (index == to - 1) {
					result->last = newNode;
				}
			}
			catch (const std::bad_alloc& e) {
				clear(result);

				throw e;
			}

		}

		index++;
		root = root->next;
	}

	return result;
}

void removeAll(DLList* list, int what) {
	if (!list->first) {
		return;
	}

	DLList::DLNode* root = list->first;
	DLList::DLNode* next = root->next;

	while (root && root->data == what) {
		next = root->next;
		next->prev = root->prev;
		delete root;
		root = next;

		list->length--;
	}

	DLList::DLNode* prev = nullptr;

	while (root) {
		if (root->data == what) {
			prev = root->prev;
			next = root->next;

			next->prev = root->prev;

			delete root;

			prev->next = next;

			list->length--;
		}

		root = root->next;
	}
}

DLList::DLNode* find(DLList* list, int what) {

	DLList::DLNode* root = list->first;

	while (root) {
		if (root->data == what) {
			return root;
		}

		root = root->next;
	}

	return nullptr;
}

void remove(DLList* list, DLList::DLNode* element) {
	if (!list->first) {
		return;
	}

	DLList::DLNode* current = list->first;

	if (list->first == element) {
		list->first = current->next;
		delete current;

		list->length--;

		return;
	}

	DLList::DLNode* prev = current;

	current = current->next;

	while (current) {
		if (current == element) {
			prev->next = current->next;
			current->next->prev = prev;

			delete current;

			list->length--;

			return;
		}

		prev = current;
		current = current->next;
	}
}

bool checkIfExist(const DLList* list, const DLList::DLNode* to, const int element) {
	if (!list->first) {
		return false;
	}

	DLList::DLNode* root = list->first;

	while (root && root != to->next) {
		if (root->data == element) {
			return true;
		}

		root = root->next;
	}

	return false;
}

void toSet(DLList* list) {
	if (!list->first) {
		return;
	}

	DLList::DLNode* current = list->first->next;
	DLList::DLNode* prev = current->prev;
	DLList::DLNode* next = current->next;

	while (current) {
		if (checkIfExist(list, prev, current->data)) {
			prev->next = current->next;
			current->next->prev = prev;
			next = current->next;

			delete current;

			list->length--;

			current = next;

			continue;
		}

		prev = current;
		current = current->next;
	}
}

void concat(DLList* first, DLList* second) {

	DLList::DLNode* firstRoot = first->first;
	DLList::DLNode* secondRoot = second->first;

	DLList::DLNode* current = nullptr;

	while (firstRoot && secondRoot) {
		if (firstRoot->data > secondRoot->data) {
			current = firstRoot;

			DLList::DLNode* newSecondRoot = secondRoot->next;

			firstRoot->prev->next = secondRoot;
			secondRoot->prev = current->prev;

			if (second->length > 1) {
				secondRoot->next->prev = nullptr;
			}

			current->prev = secondRoot;
			secondRoot->next = current;

			secondRoot = newSecondRoot;
			second->first = newSecondRoot;

			first->length++;
			second->length--;
		}
		else {
			firstRoot = firstRoot->next;
		}
	}

	second->last = nullptr;
}

void print(const DLList* list) {
	DLList::DLNode* current = list->first;

	if (!current) {
		return;
	}

	while (current) {
		std::cout << current->data << " ";
		current = current->next;
	}

	std::cout << std::endl;
}

int main() {

	DLList* first = new DLList(4);
	DLList* second = new DLList(4);

	DLList::DLNode* n1 = new DLList::DLNode(1);
	DLList::DLNode* n2 = new DLList::DLNode(2);
	DLList::DLNode* n22 = new DLList::DLNode(2);
	DLList::DLNode* n3 = new DLList::DLNode(3);
	DLList::DLNode* n4 = new DLList::DLNode(4);
	DLList::DLNode* n5 = new DLList::DLNode(5);
	DLList::DLNode* n6 = new DLList::DLNode(6);
	DLList::DLNode* n7 = new DLList::DLNode(7);

	// first list
	n1->next = n3;
	n3->prev = n1;
	n3->next = n5;
	n5->prev = n3;
	n5->next = n7;
	n7->prev = n5;

	first->first = n1;
	first->last = n7;

	// second list
	n22->next = n2;
	n2->prev = n22;
	n2->next = n4;
	n4->prev = n2;
	n4->next = n6;
	n6->prev = n4;

	second->first = n22;
	second->last = n6;

	// before concat
	print(first);
	print(second);

	concat(first, second);

	reverse(first);
	
	toSet(first);

	// after concat, reverse and toSet
	print(first);
	print(second);

	clear(first);
	clear(second);

	delete first;
	delete second;

	return 0;
}
#include <iostream>
#include <limits>

struct Node {
	int value;
	Node* left;
	Node* right;

	Node(const int value) : value(value), left(nullptr), right(nullptr) {}
};

bool isValidBST(Node* node, int min, int max) {
	if (node == nullptr) {
		return true;
	}

	if (node->value < min) {
		return false;
	} else if (node->value > max) {
		return false;
	} 

	return isValidBST(node->left, min, node->value) && isValidBST(node->right, node->value, max);
}

int main() {

	int min = std::numeric_limits<int>::min();
	int max = std::numeric_limits<int>::max();

	//correct BST
	Node* root1 = new Node(6);
	Node* lChild1 = new Node(3);
	Node* llChild1 = new Node(1);
	Node* lrChild1 = new Node(5);
	Node* rChild1 = new Node(9);
	Node* rlChild1 = new Node(7);
	Node* rrChild1 = new Node(11);

	root1->left = lChild1;
	root1->right = rChild1;

	lChild1->left = llChild1;
	lChild1->right = lrChild1;

	rChild1->left = rlChild1;
	rChild1->right = rrChild1;

	std::cout << std::boolalpha << isValidBST(root1, min, max) << std::endl;

	//incorrect BST
	Node* root2 = new Node(6);
	Node* lChild2 = new Node(3);
	Node* llChild2 = new Node(1);
	Node* lrChild2 = new Node(7);
	Node* rChild2 = new Node(8);
	Node* rlChild2 = new Node(4);
	Node* rrChild2 = new Node(11);

	root2->left = lChild2;
	root2->right = rChild2;

	lChild2->left = llChild2;
	lChild2->right = lrChild2;

	rChild2->left = rlChild2;
	rChild2->right = rrChild2;

	std::cout << std::boolalpha << isValidBST(root2, min, max) << std::endl;

	//release memory
	delete root1;
	delete lChild1;
	delete llChild1;
	delete lrChild1;
	delete rChild1;
	delete rlChild1;
	delete rrChild1;

	delete root2;
	delete lChild2;
	delete llChild2;
	delete lrChild2;
	delete rChild2;
	delete rlChild2;
	delete rrChild2;

	return 0;
}

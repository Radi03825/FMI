#include <iostream>
#include <stack>
#include <cstring>

using std::stack;

bool isBalanced(const char* expression) {

	stack<char> brackets;

	int length = strlen(expression);

	for (int i = 0; i < length; ++i) {

		switch (expression[i]) {
			case '(':
				brackets.push('(');
				break;
			case '[':
				brackets.push('(');
				break;
			case '{':
				brackets.push('(');
				break;
			case ')':
				if (brackets.top() != '(') {
					return false;
				}
				brackets.pop();
				break;
			case ']':
				if (brackets.top() != '[') {
					return false;
				}
				brackets.pop();
				break;
			case '}':
				if (brackets.top() != '{') {
					return false;
				}			
				brackets.pop();
				break;
		}

	}

	return (brackets.empty());
}

int main() {

	std::cout << std::boolalpha << isBalanced("[({})]") << std::endl;
	std::cout << std::boolalpha << isBalanced("([)]") << std::endl;

	return 0;
}
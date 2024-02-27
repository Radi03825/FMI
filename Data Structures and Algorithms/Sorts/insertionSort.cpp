#include <iostream>

// Complexity: O(N^2)
template <typename T>
void insertionSort(T* arr, size_t length) {
	for (size_t i = 1; i < length; i++) {
		T current = arr[i];

		int j = i - 1;

		while (j >= 0 && arr[j] > current) {
			arr[j + 1] = arr[j];
			j--;
		}

		arr[j + 1] = current;
	}
}

int main() {

	int arr[] = { 7, 3, 1, 5, 9, 2, 4, 6, 8 };

	insertionSort<int>(arr, 9);

	for (size_t i = 0; i < 9; i++) {
		std::cout << arr[i] << " ";
	}

	return 0;
}
#include <iostream>

// Complexity: O(N^2)
template <typename T>
void selectionSort(T* arr, size_t length) {
	for (size_t i = 0; i < length - 1; i++) {
		size_t minIndex = i;

		// First i numbers are already ordered
		for (size_t j = i + 1; j < length; j++) {
			if (arr[minIndex] > arr[j]) {
				minIndex = j;
			}
		}

		// If minIndex changed - swap
		if (minIndex != i) {
			std::swap(arr[i], arr[minIndex]);
		}
	}
}

int main() {

	int arr[] = { 7, 3, 1, 5, 9, 2, 4, 6, 8 };

	selectionSort<int>(arr, 9);

	for (size_t i = 0; i < 9; i++) {
		std::cout << arr[i] << " ";
	}

	return 0;
}
#include <iostream>

// Complexity: O(N^2)
template <typename T>
void bubbleSort(T* arr, size_t length) {

	bool hasSwaps = false;

	for (size_t i = 0; i < length - 1; i++) {
		hasSwaps = false;

		// Last i numbers are already ordered
		for (size_t j = 0; j < length - i - 1; j++) {
			if (arr[j] > arr[j + 1]) {
				std::swap(arr[j], arr[j + 1]);

				hasSwaps = true;
			}
		}

		// If no swaps were made, array is ordered
		if (!hasSwaps) {
			break;
		}
	}
}

int main() {

	int arr[] = { 7, 3, 1, 5, 9, 2, 4, 6, 8 };

	bubbleSort<int>(arr, 9);

	for (size_t i = 0; i < 9; i++) {
		std::cout << arr[i] << " ";
	}

	return 0;
}
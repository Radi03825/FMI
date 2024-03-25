#include <iostream>
#include <time.h>

template <typename T>
void pickRandomPivot(T* arr, int start, int end) {
	std::srand(time(NULL));

	int randomPivot = start + std::rand() % (end - start);

	std::swap(arr[randomPivot], arr[end]);
}

template <typename T>
int partition(T* arr, int start, int end) {

	pickRandomPivot(arr, start, end);

	T pivot = arr[end];

	int i = start;

	for (int j = start; j < end; j++) {
		if (arr[j] <= pivot) {
			std::swap(arr[j], arr[i]);
			i++;
		}
	}

	std::swap(arr[i], arr[end]);

	return i;
}

template <typename T>
void quickSort(T* arr, int start, int end) {
	if (start < end) {

		// index of partition
		int p = partition(arr, start, end);

		// over left subarray
		quickSort(arr, start, p - 1);

		// over right subarray
		quickSort(arr, p + 1, end);
	}
}



// Complexity: 
//		Average: O(N*log(N))
//		Worst: O(N^2)   
template <typename T>
void quickSort(T* arr, size_t length) {
	quickSort(arr, 0, length - 1);
}

int main() {

	int arr[] = { 7, 3, 1, 5, 9, 2, 4, 6, 8 };

	quickSort<int>(arr, 9);

	for (size_t i = 0; i < 9; i++) {
		std::cout << arr[i] << " ";
	}

	return 0;
}
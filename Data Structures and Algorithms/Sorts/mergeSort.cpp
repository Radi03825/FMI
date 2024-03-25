#include <iostream>

template <typename T>
void merge(T* arr, int p, int q, int r) {
	int n1 = q - p + 1;
	int n2 = r - q;

	int* L = new int[n1];
	int* M = new int[n2];

	for (int i = 0; i < n1; i++) {
		L[i] = arr[p + i];
	}

	for (int j = 0; j < n2; j++) {
		M[j] = arr[q + j + 1];
	}

	int i = 0, j = 0, k = p;

	while (i < n1 && j < n2) {
		if (L[i] <= M[j]) {
			arr[k] = L[i];
			i++;
		}
		else {
			arr[k] = M[j];
			j++;
		}
		k++;
	}

	while (i < n1) {
		arr[k] = L[i];
		i++;
		k++;
	}

	while (j < n2) {
		arr[k] = M[j];
		j++;
		k++;
	}

	delete[] L;
	delete[] M;
}

template <typename T>
void mergeSort(T* arr, int start, int end) {
	if (start < end) {
		int mid = start + (end - start) / 2;

		mergeSort(arr, start, mid);

		mergeSort(arr, mid + 1, end);

		// merge the sorted subarrays
		merge(arr, start, mid, end);
	}
}



// Complexity:
//		Time: O(N*log(N))
//      Space: O(N)
template <typename T>
void mergeSort(T* arr, size_t length) {
	mergeSort(arr, 0, length - 1);
}

int main() {

	int arr[] = { 7, 3, 1, 5, 9, 2, 4, 6, 8 };

	mergeSort<int>(arr, 9);

	for (size_t i = 0; i < 9; i++) {
		std::cout << arr[i] << " ";
	}

	return 0;
}
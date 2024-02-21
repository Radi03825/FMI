#include <iostream>
#include <vector>
#include <queue>

using std::vector;
using std::queue;

struct Coordinate {
	int x;
	int y;

	bool operator==(const Coordinate other) {
		return (this->x == other.x && this->y == other.y);
	}

	bool operator!=(const Coordinate other) {
		return !(*this == other);
	}
};

bool isValid(const Coordinate coord, const int k) {
	return (coord.x >= 0) && (coord.x < k) && (coord.y >= 0) && (coord.y < k);
}

bool hasPath(char** table, Coordinate start, Coordinate end, const int k) {
	vector<bool> visited(k * k);

	queue<Coordinate> queue;

	queue.push(start);

	while (!queue.empty()) {
		Coordinate current = queue.front();
		queue.pop();

		visited[current.x * k + current.y] = true;

		if (current == end) {
			return true;
		}

		Coordinate offset[] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

		for (int i = 0; i < 4; ++i) {
			Coordinate check = {current.x + offset[i].x, current.y + offset[i].y };

			if (isValid(check, k) && table[check.x][check.y] != 'x' && !visited[check.x * k + check.y]) {
				queue.push(check);
			}
		}
	}

	return false;
}

int main() {

	int k = 4;

	char** table = new char*[k];

	for (int i = 0; i < k; ++i) {
		table[i] = new char[k];
	}

	table[0][0] = ' ';
	table[0][1] = ' ';
	table[0][2] = ' ';
	table[0][3] = 'x';

	table[1][0] = 'x';
	table[1][1] = ' ';
	table[1][2] = ' ';
	table[1][3] = 'x';

	table[2][0] = 'x';
	table[2][1] = 'x';
	table[2][2] = ' ';
	table[2][3] = ' ';

	table[3][0] = ' ';
	table[3][1] = ' ';
	table[3][2] = ' ';
	table[3][3] = 'x';


	Coordinate start = { 0, 0 };
	Coordinate end = { 3, 0 };

	bool answer = hasPath(table, start, end, 4);

	std::cout << std::boolalpha << answer << std::endl;

	for (int i = 0; i < k; ++i) {
		delete[] table[i];
	}

	delete[] table;

	return 0;
}
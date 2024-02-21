#pragma once

template <typename T>
struct Node {
	T data;
	Node* next = nullptr;

	Node() = default;
	Node(const T data) : data(data) {}
};
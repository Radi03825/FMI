#include "Item.h"
#include <iostream>
#include <cstring>

Item::Item(const char* name, int amount, double price) {
    setName(name);
    setAmount(amount);
    setPrice(price);
}

Item::Item(const Item& other) {
    copyFrom(other);
}

Item& Item::operator=(const Item& other) {
    if (this != &other) {
        free();
        copyFrom(other);
    }

    return *this;
}

Item::~Item() {
    free();
}

void Item::setPrice(const double price) {
    if (price < 0) {
        throw std::logic_error("Price can not be negative number");
    }

    this->price = price;
}

void Item::setAmount(const int amount) {
    if (this->amount < 0) {
        throw std::logic_error("Amount can not be negative number");
    }

    this->amount = amount;
}

void Item::setName(const char* name) {
    if (this->name != nullptr && this->name != name) {
        free();
    }

    this->name = new char[strlen(name) + 1];
    strcpy(this->name, name);
}

double Item::getPrice() const {
    return this->price;
}

int Item::getAmount() const {
    return this->amount;
}

char* Item::getName() const {
    return this->name;
}

void Item::copyFrom(const Item& other) {
    this->name = new char[strlen(other.name) + 1];
    strcpy(this->name, other.name);

    this->amount = other.amount;
    this->price = other.price;
}

void Item::free() {
    delete[] this->name;
    this->name = nullptr;
}
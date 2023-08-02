#include "ShoppingCart.h"
#include <fstream>
#include <cstring>

namespace {
    const int INITIAL_CAPACITY = 4;
}

ShoppingCart::ShoppingCart() {
    this->items = new Item[INITIAL_CAPACITY];
    this->capacity = INITIAL_CAPACITY;
    this->size = 0;
}

ShoppingCart::ShoppingCart(const ShoppingCart& other) {
    copyFrom(other);
}

ShoppingCart& ShoppingCart::operator=(const ShoppingCart& other) {
    if (this != &other) {
        free();
        copyFrom(other);
    }

    return *this;
}

ShoppingCart::~ShoppingCart() {
    free();
}

bool ShoppingCart::addItem(const Item& item) {
    if (this->size >= this->capacity) {
        resize(this->capacity * 2);
    }

    if (find(item.getName()) == -1) {
        this->items[this->size++] = item;

        return true;
    }

    return false;
}

bool ShoppingCart::removeItem(const char* name) {
    int index = find(name);

    if (index != -1) {
        this->items[index] = this->items[this->size - 1];
        this->size--;

        if (this->size < this->capacity / 2 && this->capacity > INITIAL_CAPACITY) {
            resize(this->capacity / 2);
        }

        return true;
    }

    return false;
}

size_t ShoppingCart::itemsCount() const {
    return this->size;
}

bool ShoppingCart::exists(const char* name) const {
    return find(name) != -1;
}

bool ShoppingCart::isEmpty() const {
    return this->size == 0;
}

double ShoppingCart::getPriceOf(const char* name) const {
    int index = find(name);

    return (index != -1) ? this->items[index].getPrice() : 0.0;
}

double ShoppingCart::totalPrice() const {
    double totalPrice = 0.0;

    for (size_t i = 0; i < this->size; i++) {
        totalPrice += this->items[i].getPrice();
    }

    return totalPrice;
}

void ShoppingCart::sortByName() {
    for (size_t i = 0; i < this->size; i++) {
        Item currentItem = this->items[i];

        for (size_t j = i + 1; j < this->size; j++) {
            if (strcmp(currentItem.getName(), this->items[j].getName()) > 0) {
                Item tmp = this->items[i];

                this->items[i] = this->items[j];
                this->items[j] = tmp;
            }
        }
    }
}

bool ShoppingCart::save() const {
    char fileName[] = "shoppingCart.csv";
    std::ofstream file(fileName);

    if (!file.is_open()) {
        return false;
    }

    file << "Name,Amount,Price" << std::endl;

    for (size_t i = 0; i < this->size; i++) {
        Item currentItem = this->items[i];

        file << currentItem.getName() << "," << currentItem.getAmount() << "," << currentItem.getPrice() << std::endl;
    }

    file.close();

    return true;
}

void ShoppingCart::copyFrom(const ShoppingCart& other) {
    this->capacity = other.capacity;
    this->size = other.size;
    this->items = new Item[this->capacity];

    for (size_t i = 0; i < other.size; i++) {
        this->items[i] = other.items[i];
    }
}

void ShoppingCart::free() {
    delete[] this->items;
}

void ShoppingCart::resize(size_t capacity) {
    Item* tmp = this->items;

    this->capacity = capacity;
    this->items = new Item[this->capacity];

    for (size_t i = 0; i < this->size; i++) {
        this->items[i] = tmp[i];
    }

    delete[] tmp;
}

int ShoppingCart::find(const char* name) const {
    for (size_t i = 0; i < this->size; i++) {
        if (strcmp(this->items[i].getName(), name) == 0) {
            return i;
        }
    }

    return -1;
}

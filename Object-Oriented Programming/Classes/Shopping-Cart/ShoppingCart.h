#pragma once
#include "Item.h"

class ShoppingCart {

    private:
        Item* items;
        size_t capacity;
        size_t size;
    
    public:
        ShoppingCart();
        ShoppingCart(const ShoppingCart&);

        ShoppingCart& operator=(const ShoppingCart&);

        ~ShoppingCart();

        bool addItem(const Item&);
        bool removeItem(const char*);
        size_t itemsCount() const;
        bool exists(const char*) const;
        bool isEmpty() const;
        double getPriceOf(const char*) const;
        double totalPrice() const;
        void sortByName();
        bool save() const;

    private:
        void copyFrom(const ShoppingCart&);
        void free();
        void resize(size_t);
        int find(const char*) const;
};

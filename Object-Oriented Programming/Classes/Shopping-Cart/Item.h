#pragma once

class Item {

    private:
        double price;
        int amount;
        char* name = nullptr;

    public:

        Item() = default;
        Item(const char*, int, double);

        Item(const Item& other);
        Item& operator=(const Item&);

        ~Item();

        void setPrice(const double);
        void setAmount(const int);
        void setName(const char*);

        double getPrice() const;
        int getAmount() const;
        char* getName() const;

    private:
        void copyFrom(const Item&);
        void free();
};
#include <iostream>
#include "Item.h"
#include "ShoppingCart.h"

using std::cout, std::endl;

int main() {

    ShoppingCart cart;

    Item item1("Item1", 3, 5.5);
    Item item2("Item2", 5, 3.5);

    cout << cart.addItem(item1) << endl;

    cout << cart.addItem(item2) << endl;

    cout << cart.exists("Item1") << endl;

    cout << cart.save();

    cout << cart.removeItem("Item1") << endl;

    cout << cart.exists("Item1") << endl;

    return 0;
}
#include <iostream>
#include <fstream>

using std::cin, std::cout, std::endl, std::ofstream, std::ifstream;

const size_t MAX_PAIR_COUNT = 25;
const char FILE_NAME[] = "relation.txt";

struct Pair {
    int a;
    int b;

    void initPair() {
        cout << "a = ";
        cin >> a;
        cout << "b = ";
        cin >> b;
    }

    void printPair() {
        cout << "a = " << a << ", b = " << b << endl;
    }
};

struct Relation {
    Pair pairs[MAX_PAIR_COUNT];
    size_t size = 0;

    bool addPair(const Pair& pair) {
        if (size == MAX_PAIR_COUNT) {
            cout << "Max pairs count reached!" << endl;

            return false;
        }

        pairs[size++] = pair;

        return true;
    }

    void printRelation() {
        cout << "Pairs:" << endl;

        for (int i = 0; i < size; i++) {
            pairs[i].printPair();
        }

        cout << "Count: " << size << endl;
    }
};

void readPairFromFile(ifstream& file, Pair& pair) {
    file >> pair.a >> pair.b;
}

void writePairToFile(ofstream& file, const Pair& pair) {
    file << pair.a << " " << pair.b << endl;
}

void readRelationFromFile(const char fileName[], Relation& relation) {
    ifstream file(fileName);

    if (!file.is_open()) {
        cout << "Error" << endl;
        return;
    }

    file >> relation.size;

    for (int i = 0; i < relation.size; i++) {
        readPairFromFile(file, relation.pairs[i]);
    }

    file.close();
}

void writeRelationToFile(const char fileName[], const Relation& relation) {
    ofstream file(fileName);

    if (!file.is_open()) {
        cout << "Error" << endl;
        return;
    }

    file << relation.size << endl;

    for (int i = 0; i < relation.size; i++) {
        writePairToFile(file, relation.pairs[i]);
    }

    file.close();
}

int main() {
    
    int pairCount;

    Relation relation;

    cout << "Enter pairs count: ";
    cin >> pairCount;

    for (int i = 0; i < pairCount; i++) {
        Pair pair;
        pair.initPair();
        relation.addPair(pair);
    }

    relation.printRelation();
    writeRelationToFile(FILE_NAME, relation);

    Relation readRelation;
    readRelationFromFile(FILE_NAME, readRelation);
    readRelation.printRelation();

    return 0;
}
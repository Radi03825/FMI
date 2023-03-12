#include <iostream>

using std::cin, std::cout, std::endl;

enum Genre {
    Comedy, 
    Action, 
    Horror, 
    Romantic
};

struct Movie {
    char* name;
    int duration;
    Genre genre;
    double rating;

    void createMovie(char* name, int duration, Genre genre, double rating) {
        this->name = name;
        this->duration = duration;
        this->genre = genre;
        this->rating = rating;
    }

    char* getGenre() {
        switch (genre) {
            case Comedy:
                return "Comedy";
            case Action:
                return "Action";
            case Horror:
                return "Horror";
            case Romantic:
                return "Romantic";
            default:
                return "Genre not found.";
        }
    }

    void printMovieInfo() {
        cout << "Movie info:" << endl;
        cout << " Name: " << name << endl << " Duration(minutes): " << duration << endl << " Genre: " << getGenre() << endl << " Rating: " << rating << endl;
    }
};

void printTopFilmByRating(Movie* movies, int size) {
    if (size == 0) {
        cout << "Array is empty.";
        return;
    }

    Movie top = movies[0];
    int maxRating = movies[0].rating;

    for (int i = 1; i < size; i++) {
        if (movies[i].rating > maxRating) {
            top = movies[i];
            maxRating = movies[i].rating;
        }
    }

    cout << "Movie with highest rating: " << top.name << endl;
    top.printMovieInfo();
}

int main() {

    Movie m1 = {"Fast & Furious 2", 120, Genre::Action, 9.9};
    Movie m2 = {"Fast & Furious 3", 124, Genre::Action, 9.0};
    Movie m3 = {"Fast & Furious 5", 131, Genre::Action, 7.9};
    
    m2.printMovieInfo();

    Movie movies[3] = {m1, m2, m3};

    printTopFilmByRating(movies, 3);


    return 0;
}
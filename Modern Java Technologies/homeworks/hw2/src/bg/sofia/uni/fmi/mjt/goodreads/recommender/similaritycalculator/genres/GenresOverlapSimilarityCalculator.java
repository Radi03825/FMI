package bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.genres;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.SimilarityCalculator;

import java.util.List;

public class GenresOverlapSimilarityCalculator implements SimilarityCalculator {

    @Override
    public double calculateSimilarity(Book first, Book second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Books cannot be null");
        }

        List<String> firstBookGenres = first.genres();
        List<String> secondBookGenres = second.genres();

        if (firstBookGenres == null || secondBookGenres == null ||
            firstBookGenres.isEmpty() || secondBookGenres.isEmpty()) {
            return 0;
        }

        long commonGenresCount = firstBookGenres.stream()
            .filter(secondBookGenres::contains)
            .count();

        int minSize = Math.min(firstBookGenres.size(), secondBookGenres.size());

        return (double) commonGenresCount / minSize;
    }

}
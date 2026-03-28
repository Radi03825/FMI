package bg.sofia.uni.fmi.mjt.goodreads.recommender;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.SimilarityCalculator;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BookRecommender implements BookRecommenderAPI {

    private Set<Book> initialBooks;
    private SimilarityCalculator calculator;

    public BookRecommender(Set<Book> initialBooks, SimilarityCalculator calculator) {
        this.initialBooks = initialBooks;
        this.calculator = calculator;
    }

    @Override
    public SortedMap<Book, Double> recommendBooks(Book origin, int maxN) {
        if (origin == null) {
            throw new IllegalArgumentException("Origin book cannot be null");
        } else if (maxN <= 0) {
            throw new IllegalArgumentException("Max number of books to recommend cannot be negative");
        }

        Map<Book, Double> topNScores = initialBooks.stream()
            .collect(Collectors.toMap(
                book -> book,
                book -> calculator.calculateSimilarity(origin, book)
            )).entrySet().stream()
            .sorted(Map.Entry.<Book, Double>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry.comparingByKey(Comparator.comparing(Book::ID))))
            .limit(maxN)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
            ));

        SortedMap<Book, Double> sortedTopNScores = new TreeMap<>((firstBook, secondBook) -> {
            int comparedBySimilarity = Double.compare(topNScores.get(firstBook), topNScores.get(secondBook));

            return comparedBySimilarity != 0 ? comparedBySimilarity : firstBook.ID().compareTo(secondBook.ID());
        });

        sortedTopNScores.putAll(topNScores);
        return sortedTopNScores;
    }

}
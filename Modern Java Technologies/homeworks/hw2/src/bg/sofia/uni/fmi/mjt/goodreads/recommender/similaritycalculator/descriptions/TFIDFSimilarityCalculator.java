package bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.descriptions;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.SimilarityCalculator;
import bg.sofia.uni.fmi.mjt.goodreads.tokenizer.TextTokenizer;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TFIDFSimilarityCalculator implements SimilarityCalculator {

    private Set<Book> books;
    private TextTokenizer tokenizer;

    public TFIDFSimilarityCalculator(Set<Book> books, TextTokenizer tokenizer) {
        this.books = books;
        this.tokenizer = tokenizer;
    }

    /*
     * Do not modify!
     */
    @Override
    public double calculateSimilarity(Book first, Book second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Books cannot be null");
        }

        Map<String, Double> tfIdfScoresFirst = computeTFIDF(first);
        Map<String, Double> tfIdfScoresSecond = computeTFIDF(second);

        return cosineSimilarity(tfIdfScoresFirst, tfIdfScoresSecond);
    }

    public Map<String, Double> computeTFIDF(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        Map<String, Double> tfs = computeTF(book);
        Map<String, Double> idfs = computeIDF(book);

        return idfs.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue() * tfs.getOrDefault(entry.getKey(), 0.0)
            ));
    }

    public Map<String, Double> computeTF(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        List<String> words = tokenizer.tokenize(book.description());
        int wordsCount = words.size();

        Map<String, Long> countOfWords = words.stream()
            .collect(Collectors.groupingBy(
                word -> word,
                Collectors.counting()
            ));

        return countOfWords.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> (double) entry.getValue() / wordsCount
            ));
    }

    public Map<String, Double> computeIDF(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        List<String> bookTokens = tokenizer.tokenize(book.description());
        int booksCount = this.books.size();

        Map<String, Long> wordInBooksCount = bookTokens.stream()
            .distinct()
            .collect(Collectors.toMap(
                word -> word,
                word -> this.books.stream()
                    .map(b -> tokenizer.tokenize(b.description()))
                    .filter(words -> words.contains(word))
                    .count()
            ));

        return bookTokens.stream()
            .distinct()
            .collect(Collectors.toMap(
                word -> word,
                word -> {
                    Long occurrenceCount = wordInBooksCount.get(word);
                    return (occurrenceCount != 0) ? Math.log((double) booksCount / occurrenceCount) : 0;
                }
            ));
    }

    private double cosineSimilarity(Map<String, Double> first, Map<String, Double> second) {
        double magnitudeFirst = magnitude(first.values());
        double magnitudeSecond = magnitude(second.values());

        return dotProduct(first, second) / (magnitudeFirst * magnitudeSecond);
    }

    private double dotProduct(Map<String, Double> first, Map<String, Double> second) {
        Set<String> commonKeys = new HashSet<>(first.keySet());
        commonKeys.retainAll(second.keySet());

        return commonKeys.stream()
            .mapToDouble(word -> first.get(word) * second.get(word))
            .sum();
    }

    private double magnitude(Collection<Double> input) {
        double squaredMagnitude = input.stream()
            .map(v -> v * v)
            .reduce(0.0, Double::sum);

        return Math.sqrt(squaredMagnitude);
    }
    
}
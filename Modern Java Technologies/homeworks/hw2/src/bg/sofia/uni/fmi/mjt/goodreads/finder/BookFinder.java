package bg.sofia.uni.fmi.mjt.goodreads.finder;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import bg.sofia.uni.fmi.mjt.goodreads.tokenizer.TextTokenizer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookFinder implements BookFinderAPI {

    private Set<Book> books;
    private TextTokenizer tokenizer;

    public BookFinder(Set<Book> books, TextTokenizer tokenizer) {
        setBooks(books);
        this.tokenizer = tokenizer;
    }

    public Set<Book> allBooks() {
        return this.books;
    }

    @Override
    public List<Book> searchByAuthor(String authorName) {
        if (authorName == null || authorName.isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be null or empty");
        }

        return this.books.stream()
            .filter(book -> book.author().equals(authorName))
            .collect(Collectors.toList());
    }

    @Override
    public Set<String> allGenres() {
        return this.books.stream()
            .flatMap(book -> book.genres().stream())
            .collect(Collectors.toSet());
    }

    @Override
    public List<Book> searchByGenres(Set<String> genres, MatchOption option) {
        if (genres == null) {
            throw new IllegalArgumentException("Genres cannot be null");
        }

        return this.books.stream()
            .filter(book -> {
                return switch (option) {
                    case MatchOption.MATCH_ANY -> book.genres().stream().anyMatch(genres::contains);
                    case MatchOption.MATCH_ALL -> new HashSet<>(book.genres()).containsAll(genres);
                };
            })
            .toList();
    }

    @Override
    public List<Book> searchByKeywords(Set<String> keywords, MatchOption option) {
        if (keywords == null) {
            throw new IllegalArgumentException("Keywords cannot be null");
        }

        return this.books.stream()
            .filter(book -> {
                List<String> titleTokens = tokenizer.tokenize(book.title());
                List<String> descriptionTokens = tokenizer.tokenize(book.description());

                Set<String> allTokens = new HashSet<>(titleTokens);
                allTokens.addAll(descriptionTokens);

                return switch (option) {
                    case MatchOption.MATCH_ANY -> allTokens.stream().anyMatch(keywords::contains);
                    case MatchOption.MATCH_ALL -> allTokens.containsAll(keywords);
                };
            })
            .toList();
    }

    public void setBooks(Set<Book> books) {
        if (books == null) {
            throw new IllegalArgumentException("Books cannot be null");
        }

        this.books = books;
    }
    
}
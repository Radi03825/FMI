package bg.sofia.uni.fmi.mjt.goodreads.book;

import java.util.Arrays;
import java.util.List;

public record Book(
    String ID,
    String title,
    String author,
    String description,
    List<String> genres,
    double rating,
    int ratingCount,
    String URL
) {

    private static final int ID_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int AUTHOR_INDEX = 2;
    private static final int DESCRIPTION_INDEX = 3;
    private static final int GENRES_INDEX = 4;
    private static final int RATING_INDEX = 5;
    private static final int RATING_COUNT_INDEX = 6;
    private static final int URL_INDEX = 7;

    private static final int EXPECTED_TOKENS_COUNT = 8;

    public static Book of(String[] tokens) {
        if (tokens == null || tokens.length != EXPECTED_TOKENS_COUNT) {
            throw new IllegalArgumentException("Invalid book tokens");
        }

        List<String> genres =
            Arrays.stream(tokens[GENRES_INDEX].substring(1, tokens[GENRES_INDEX].length() - 1).split(","))
                .map(s -> s.replaceAll("'", ""))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        String id = tokens[ID_INDEX];
        String title = tokens[TITLE_INDEX];
        String author = tokens[AUTHOR_INDEX];
        String description = tokens[DESCRIPTION_INDEX];
        double rating = Double.parseDouble(tokens[RATING_INDEX]);
        int ratingCount = Integer.parseInt(tokens[RATING_COUNT_INDEX].replaceAll(",", ""));
        String url = tokens[URL_INDEX];

        return new Book(id, title, author, description, genres, rating, ratingCount, url);
    }
    
}
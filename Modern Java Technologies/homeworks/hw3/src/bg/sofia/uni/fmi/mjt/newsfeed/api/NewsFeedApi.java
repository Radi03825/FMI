package bg.sofia.uni.fmi.mjt.newsfeed.api;

import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.ApiException;
import bg.sofia.uni.fmi.mjt.newsfeed.response.ApiResponse;

@FunctionalInterface
public interface NewsFeedApi {

    /**
     * Search for news articles from the News Feed API.
     *
     * @return an {@code ApiResponse} object containing the search results
     * @throws ApiException if an error occurs during the search process
     */
    ApiResponse search() throws ApiException;

    /**
     * Creates a new {@code NewsFeedBuilder} instance to configure and perform advanced searches.
     *
     * @param keywords the keywords to search for in the news feed.
     *                 Keywords are used to filter the news articles.
     * @return a new {@code NewsFeedBuilder} instance to configure the search
     */
    static NewsFeedBuilder newBuilder(String... keywords) {
        return new NewsFeedBuilder(keywords);
    }

}

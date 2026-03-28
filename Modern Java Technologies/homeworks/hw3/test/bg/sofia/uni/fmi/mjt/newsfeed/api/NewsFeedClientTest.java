package bg.sofia.uni.fmi.mjt.newsfeed.api;

import bg.sofia.uni.fmi.mjt.newsfeed.dto.Article;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.ApiException;
import bg.sofia.uni.fmi.mjt.newsfeed.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NewsFeedClientTest {

    private NewsFeedClient newsFeedClient;
    private NewsFeedBuilder newsFeedBuilder;
    private HttpClient httpClient;

    @BeforeEach
    public void setUp() {
        newsFeedBuilder = new NewsFeedBuilder(new String[] {"keyword"})
            .category("category")
            .country("country")
            .page(1)
            .pageSize(10);

        httpClient = mock(HttpClient.class);
        newsFeedClient = new NewsFeedClient(newsFeedBuilder, httpClient, "apiKey");
    }

    @Test
    public void testSearchWithNullKeywords() {
        NewsFeedBuilder newsFeedBuilderNull = new NewsFeedBuilder(null);
        NewsFeedClient newsFeedClientNull = new NewsFeedClient(newsFeedBuilderNull, httpClient);

        assertThrows(IllegalArgumentException.class, () -> newsFeedClientNull.search(), "Keywords cannot be null");
    }

    @Test
    public void testSearchWithEmptyKeywords() {
        NewsFeedBuilder newsFeedBuilderEmpty = new NewsFeedBuilder(new String[0]);
        NewsFeedClient newsFeedClientEmpty = new NewsFeedClient(newsFeedBuilderEmpty, httpClient);

        assertThrows(IllegalArgumentException.class, () -> newsFeedClientEmpty.search(), "Keywords cannot be empty");
    }

    @Test
    public void testSearchValidRequest() throws InterruptedException, ApiException, IOException {
        HttpResponse<Object> stringHttpResponse = mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(stringHttpResponse);
        when(stringHttpResponse.statusCode()).thenReturn(200);
        when(stringHttpResponse.body()).thenReturn("{\"status\":\"ok\",\"totalResults\":0,\"articles\":[]}");

        ApiResponse result = newsFeedClient.search();
        ApiResponse expected = new ApiResponse("ok", 0, List.of());

        assertEquals(expected, result, "The response should be ok with 0 articles");
    }

    @Test
    public void testSearchValidRequestWithArticles() throws InterruptedException, ApiException, IOException {
        HttpResponse<Object> stringHttpResponse = mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(stringHttpResponse);
        when(stringHttpResponse.statusCode()).thenReturn(200);
        when(stringHttpResponse.body()).thenReturn("{\"status\":\"ok\",\"totalResults\":1," +
            "\"articles\":[{\"source\":{\"id\":\"id\",\"name\":\"name\"},\"author\":\"author\",\"title\":\"title\"," +
            "\"description\":\"description\",\"url\":\"url\",\"urlToImage\":\"urlToImage\",\"publishedAt\":\"2023-03-25T10:15:30\",\"content\":\"content\"}]}");

        ApiResponse result = newsFeedClient.search();
        ApiResponse expected = new ApiResponse("ok", 1, List.of(new Article("author", "title", "description", "url", "urlToImage", LocalDateTime.of(2023, 3, 25, 10, 15, 30), "content")));

        assertEquals(expected, result, "The response should be ok with 1 article");
    }

    @Test
    public void testSearchWithServiceException() throws IOException, InterruptedException, ApiException {
        when(httpClient.send(any(), any())).thenThrow(new IOException("Failed to connect to the service"));

        ApiException exception = assertThrows(ApiException.class, () -> {
            newsFeedClient.search();
        });

        String message = exception.getMessage();

        assertEquals(Integer.valueOf(500), exception.getStatusCode(), "The status code should be 500");
        assertEquals( "Failed to connect to the service", exception.getMessage(), "The message should be 'Failed to connect to the service'");
    }

    @Test
    public void testSearchWithApiException() throws IOException, InterruptedException, ApiException {
        HttpResponse<Object> stringHttpResponse = mock(HttpResponse.class);
        when(httpClient.send(any(), any())).thenReturn(stringHttpResponse);
        when(stringHttpResponse.statusCode()).thenReturn(400);
        when(stringHttpResponse.body()).thenReturn("{\"status\":\"error\",\"code\":\"apiKeyMissing\"}");

        ApiException exception = assertThrows(ApiException.class, () -> {
            newsFeedClient.search();
        });

        String message = exception.getMessage();

        assertEquals(Integer.valueOf(400), exception.getStatusCode(), "The status code should be 400");
        assertEquals("apiKeyMissing", exception.getCode(), "The code should be 'apiKeyMissing'");
    }
    
}

package bg.sofia.uni.fmi.mjt.newsfeed.api;

import bg.sofia.uni.fmi.mjt.newsfeed.adapter.LocalDateTimeAdapter;
import bg.sofia.uni.fmi.mjt.newsfeed.dto.ErrorDTO;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.ApiException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.factory.ExceptionFactory;
import bg.sofia.uni.fmi.mjt.newsfeed.response.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Properties;

public class NewsFeedClient implements NewsFeedApi {

    private static final Integer SUCCESS_STATUS_CODE = 200;
    private static final Integer SERVICE_ERROR_STATUS_CODE = 500;

    private static final String API_SCHEME = "http";
    private static final String API_HOST = "newsapi.org";
    private static final String API_PATH = "/v2/top-headlines";
    private static final String REQUEST_KEYWORDS_PREFIX = "q=%s";
    private static final String REQUEST_KEYWORDS_SEPARATOR = "+";
    private static final String REQUEST_COUNTRY_PREFIX = "&country=%s";
    private static final String REQUEST_CATEGORY_PREFIX = "&category=%s";
    private static final String REQUEST_PAGE_PREFIX = "&page=%d";
    private static final String REQUEST_PAGE_SIZE_PREFIX = "&pageSize=%d";

    private static Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
        .create();

    private final HttpClient httpClient;

    private String apiKey;

    private String[] keywords;
    private String country;
    private String category;
    private Integer page;
    private Integer pageSize;

    public NewsFeedClient(NewsFeedBuilder builder, HttpClient httpClient) {
        this.keywords = builder.getKeywords();
        this.country = builder.getCountry();
        this.category = builder.getCategory();
        this.page = builder.getPage();
        this.pageSize = builder.getPageSize();
        this.httpClient = httpClient;
    }

    public NewsFeedClient(NewsFeedBuilder builder, HttpClient httpClient, String apiKey) {
        this.keywords = builder.getKeywords();
        this.country = builder.getCountry();
        this.category = builder.getCategory();
        this.page = builder.getPage();
        this.pageSize = builder.getPageSize();
        this.httpClient = httpClient;
        this.apiKey = apiKey;
    }

    @Override
    public ApiResponse search() throws ApiException {
        if (keywords == null || keywords.length == 0) {
            throw new IllegalArgumentException("Keywords cannot be null");
        }

        HttpResponse<String> stringHttpResponse;
        try {
            loadApiKey();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(createUri())
                .header("X-Api-Key", apiKey)
                .build();

            stringHttpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | URISyntaxException | IOException e) {
            throw new ApiException(SERVICE_ERROR_STATUS_CODE, "Internal server error", e.getMessage());
        }

        int statusCode = stringHttpResponse.statusCode();
        if (statusCode != SUCCESS_STATUS_CODE) {
            ErrorDTO errorDTO = gson.fromJson(stringHttpResponse.body(), ErrorDTO.class);
            throw ExceptionFactory.createException(statusCode, errorDTO);
        }

        Type type = new TypeToken<ApiResponse>() { }.getType();
        return gson.fromJson(stringHttpResponse.body(), type);
    }

    private URI createUri() throws URISyntaxException {
        StringBuilder uri = new StringBuilder(
            String.format(REQUEST_KEYWORDS_PREFIX, String.join(REQUEST_KEYWORDS_SEPARATOR, keywords))
        );

        if (country != null && !country.trim().isEmpty()) {
            uri.append(String.format(REQUEST_COUNTRY_PREFIX, country));
        }

        if (category != null && !category.trim().isEmpty()) {
            uri.append(String.format(REQUEST_CATEGORY_PREFIX, category));
        }

        if (page != null) {
            uri.append(String.format(REQUEST_PAGE_PREFIX, page));
        }

        if (pageSize != null) {
            uri.append(String.format(REQUEST_PAGE_SIZE_PREFIX, pageSize));
        }

        return new URI(API_SCHEME, API_HOST, API_PATH, uri.toString(), null);
    }

    private void loadApiKey() throws IOException {
        if (apiKey != null) {
            return;
        }

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/resources/config.properties");
        properties.load(fis);
        this.apiKey = properties.getProperty("api.key");
    }

}

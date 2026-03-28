import bg.sofia.uni.fmi.mjt.newsfeed.api.NewsFeedApi;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.ApiException;
import bg.sofia.uni.fmi.mjt.newsfeed.exceptions.BadRequestException;
import bg.sofia.uni.fmi.mjt.newsfeed.response.ApiResponse;
import java.net.http.HttpClient;

public class Main {

    public static void main(String[] args) throws Exception {

        NewsFeedApi newsFeedApi = NewsFeedApi.newBuilder("football")
            .page(1)
            .build(HttpClient.newHttpClient());

        ApiResponse response = newsFeedApi.search();

        System.out.println(response);

        System.out.println(response.status());

        System.out.println(response.totalResults());
        System.out.println(response.articles().size());

        List<Article> articles = response.articles();
        for (Article article : articles) {
            System.out.println(article);
        }
    }

}

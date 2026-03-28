package bg.sofia.uni.fmi.mjt.newsfeed.dto;

import java.time.LocalDateTime;

public record Article(String author, String title, String description,
                      String url, String urlToImage,
                      LocalDateTime publishedAt, String content) {

}

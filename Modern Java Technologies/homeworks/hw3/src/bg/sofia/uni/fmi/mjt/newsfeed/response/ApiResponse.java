package bg.sofia.uni.fmi.mjt.newsfeed.response;

import bg.sofia.uni.fmi.mjt.newsfeed.dto.Article;

import java.util.List;

public record ApiResponse(String status, Integer totalResults, List<Article> articles) {

}

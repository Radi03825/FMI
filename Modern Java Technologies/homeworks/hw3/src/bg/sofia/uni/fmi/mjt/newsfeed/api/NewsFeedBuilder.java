package bg.sofia.uni.fmi.mjt.newsfeed.api;

import java.net.http.HttpClient;

public class NewsFeedBuilder {

    private String[] keywords;
    private String country;
    private String category;
    private Integer page;
    private Integer pageSize;

    public NewsFeedBuilder(String[] keywords) {
        this.keywords = keywords;
    }

    public NewsFeedBuilder country(String country) {
        this.country = country;
        return this;
    }

    public NewsFeedBuilder category(String category) {
        this.category = category;
        return this;
    }

    public NewsFeedBuilder page(Integer page) {
        this.page = page;
        return this;
    }

    public NewsFeedBuilder pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public NewsFeedClient build(HttpClient client) {
        return new NewsFeedClient(this, client);
    }

    public String[] getKeywords() {
        return keywords;
    }

    public String getCountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }
    
}

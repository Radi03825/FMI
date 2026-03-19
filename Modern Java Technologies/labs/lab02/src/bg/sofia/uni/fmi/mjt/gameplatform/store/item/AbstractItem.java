package bg.sofia.uni.fmi.mjt.gameplatform.store.item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public abstract class AbstractItem implements StoreItem {

    private String title;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private int ratesSum;
    private int ratesCount;

    public AbstractItem(String title, BigDecimal price, LocalDateTime releaseDate) {
        setTitle(title);
        setPrice(price);
        setReleaseDate(releaseDate);

        this.ratesSum = 0;
        this.ratesCount = 0;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public double getRating() {
        return (ratesSum * 1.0 / ratesCount);
    }

    @Override
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public void rate(double rating) {
        this.ratesSum += rating;
        this.ratesCount++;
    }
}

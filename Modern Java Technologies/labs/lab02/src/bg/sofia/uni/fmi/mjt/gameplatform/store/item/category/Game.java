package bg.sofia.uni.fmi.mjt.gameplatform.store.item.category;

import bg.sofia.uni.fmi.mjt.gameplatform.store.item.AbstractItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Game extends AbstractItem {

    private String genre;

    public Game(String title, BigDecimal price, LocalDateTime releaseDate, String genre) {
        super(title, price, releaseDate);
        setGenre(genre);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
}

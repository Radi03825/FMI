package bg.sofia.uni.fmi.mjt.gameplatform.store.item.filter;

import bg.sofia.uni.fmi.mjt.gameplatform.store.item.StoreItem;

import java.time.LocalDateTime;

public class ReleaseDateItemFilter implements ItemFilter {

    private LocalDateTime lowerBound;
    private LocalDateTime upperBound;

    public ReleaseDateItemFilter(LocalDateTime lowerBound, LocalDateTime upperBound) {
        setLowerBound(lowerBound);
        setUpperBound(upperBound);
    }

    @Override
    public boolean matches(StoreItem item) {
        return (lowerBound.compareTo(item.getReleaseDate()) <= 0) && (upperBound.compareTo(item.getReleaseDate()) >= 0);
    }

    public LocalDateTime getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(LocalDateTime lowerBound) {
        this.lowerBound = lowerBound;
    }

    public LocalDateTime getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(LocalDateTime upperBound) {
        this.upperBound = upperBound;
    }
    
}

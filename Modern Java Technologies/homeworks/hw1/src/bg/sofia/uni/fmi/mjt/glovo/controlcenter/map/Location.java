package bg.sofia.uni.fmi.mjt.glovo.controlcenter.map;

import java.util.Objects;

public record Location(int x, int y) implements Comparable<Location> {

    @Override
    public int compareTo(Location o) {
        return x != o.x ? Integer.compare(x, o.x) : Integer.compare(y, o.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
}

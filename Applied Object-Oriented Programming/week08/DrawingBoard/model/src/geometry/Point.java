package geometry;

import java.util.Arrays;

public class Point {
    private int[] coords;

    public Point(int[] coords) {
        setCoords(coords);
    }

    public Point() {
        this(new int[2]);
    }

    public Point(Point point) {
        this(point.getCoords());
    }

    public int[] getCoords() {
        int[] copy = new int[coords.length];
        System.arraycopy(coords, 0, copy, 0, coords.length);

        return copy;
    }

    public void setCoords(int[] coords) {
        if (coords != null && coords.length == 2) {
            this.coords = new int[coords.length];

            for (int i = 0; i < coords.length; i++) {
                this.coords[i] = coords[i];
            }
        } else {
            this.coords = new int[] {0, 0};
        }
    }

    @Override
    public String toString() {
        return String.format("(%d; %d)", coords[0], coords[1]);
    }
}

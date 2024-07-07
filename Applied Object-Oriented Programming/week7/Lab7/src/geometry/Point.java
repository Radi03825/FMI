package geometry;

import java.util.Arrays;

public class Point {
    private double[] coords;

    public Point(double[] coords) {
        setCoords(coords);
    }

    public Point() {
        this(new double[2]);
    }

    public Point(Point point) {
        this(point.coords);
    }
    public double[] getCoords() {
        double[] copy = new double[coords.length];

        for (int i = 0; i < copy.length; i++) {
            copy[i] = coords[i];
        }

        return copy;
    }

    public void setCoords(double[] coords) {
        if (coords != null && coords.length == 2) {
            double[] copy = new double[coords.length];

            for (int i = 0; i < copy.length; i++) {
                copy[i] = coords[i];
            }

            this.coords = copy;
        } else {
            this.coords = new double[2];
        }
    }

    @Override
    public String toString() {
        return String.format("(%.2f; %.2f)", coords[0], coords[1]);
    }
}

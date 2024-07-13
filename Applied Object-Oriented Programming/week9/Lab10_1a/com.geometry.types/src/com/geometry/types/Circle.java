package com.geometry.types;

public class Circle extends Point {
    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        setRadius(radius);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = 1;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }

        if (o instanceof Circle circle) {
            int resultFromPoint = super.compareTo(o);

            if (resultFromPoint == 0) {
                return this.radius - circle.radius;
            } else {
                return resultFromPoint;
            }
        }

        return 1;
    }

    @Override
    public String toString() {
        return String.format("Circle: (%s), radius: %d", super.toString(), radius);
    }
}

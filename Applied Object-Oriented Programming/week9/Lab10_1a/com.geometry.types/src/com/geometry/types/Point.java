package com.geometry.types;

public class Point extends Shape {
    private int x;
    private int y;

    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }

        if (o instanceof Point) {
            Point point = (Point) o;

            int resultFromX = this.x - point.x;

            if (resultFromX == 0) {
                return this.y - point.y;
            } else {
                return resultFromX;
            }
        }

        return 1;
    }

    @Override
    public String toString() {
        return String.format("Point: (%d; %d)", x, y);
    }
}

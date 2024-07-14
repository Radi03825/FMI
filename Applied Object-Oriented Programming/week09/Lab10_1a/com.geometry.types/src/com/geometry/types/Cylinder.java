package com.geometry.types;

public class Cylinder extends Circle {
    private int height;

    public Cylinder(int x, int y, int radius, int height) {
        super(x, y, radius);
        setHeight(height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        } else {
            this.height = 1;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }

        if (o instanceof Cylinder cylinder) {
            int resultFromCircle = super.compareTo(o);

            if (resultFromCircle == 0) {
                return this.height - cylinder.height;
            } else {
                return resultFromCircle;
            }
        }

        return 1;
    }

    @Override
    public String toString() {
        return String.format("Cylinder: (%s), height: %d", super.toString(), height);
    }
}

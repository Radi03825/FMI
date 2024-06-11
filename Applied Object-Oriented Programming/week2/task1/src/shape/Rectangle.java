package shape;

public class Rectangle {
    private double width;
    private double height;
    private static String color = "yellow";

    private String symbol;

    public Rectangle() {
        width = 1;
        height = 1;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public static String getColor() {
        return color;
    }

    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        } else {
            this.width = 1;
        }
    }

    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        } else {
            this.height = 1;
        }
    }

    public static void setColor(String color) {
        if (color != null) {
            Rectangle.color = color;
        } else {
            Rectangle.color = "yellow";
        }
    }
}

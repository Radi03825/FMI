package geometry;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Rectangle {
    private Point uPoint;
    private double width;
    private double height;

    public Rectangle(Point uPoint, double width, double height) {
        setuPoint(uPoint);
        setWidth(width);
        setHeight(height);
    }

    public Rectangle() {
        this(new Point(), 10, 10);
    }

    public Rectangle(Rectangle rectangle) {
        this(rectangle.uPoint, rectangle.width, rectangle.height);
    }

    public Point getuPoint() {
        return new Point(uPoint);
    }

    public void setuPoint(Point uPoint) {
        if (uPoint != null) {
            this.uPoint = new Point(uPoint);
        } else {
            this.uPoint = new Point();
        }
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        } else {
            this.width = 10;
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height > 10) {
            this.height = height;
        } else {
            this.height = 10;
        }
    }

    public void draw(Group pane) {
        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(uPoint.getCoords()[0], uPoint.getCoords()[1], width, height);

        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);

        pane.getChildren().add(rectangle);
    }

    @Override
    public String toString() {
        return String.format("uPoint: %s, width: %.2f, height: %.2f", uPoint, width, height);
    }
}

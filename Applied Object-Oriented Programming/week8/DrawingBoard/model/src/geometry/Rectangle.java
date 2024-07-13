package geometry;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Rectangle extends Point { // upper left corner
    private Point lPoint; // lower right corner

    public Rectangle(int[] coords, Point lPoint) {
        super(coords);
        setlPoint(lPoint);
    }

    public Rectangle() {
        this(new int[2], new Point(new int[]{1, 1}));
    }

    public Rectangle(Rectangle rectangle) {
        this(rectangle.getCoords(), rectangle.getlPoint());
    }

    public Point getlPoint() {
        return new Point(lPoint);
    }

    public void setlPoint(Point lPoint) {
        if (lPoint != null) {
            this.lPoint = new Point(lPoint);
        } else {
            this.lPoint = new Point();
        }
    }

    public int measure() {
        int[] uCoords = this.getCoords();
        int[] lCoords = lPoint.getCoords();

        int width = Math.abs(uCoords[0] - lCoords[0]);
        int height = Math.abs(uCoords[1] - lCoords[1]);

        return 2 * (width + height);
    }

    public void draw(Pane pane) {
        int[] uCoords = this.getCoords();
        int[] lCoords = lPoint.getCoords();

        int width = Math.abs(uCoords[0] - lCoords[0]);
        int height = Math.abs(uCoords[1] - lCoords[1]);

        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(uCoords[0], uCoords[1], width, height);

        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(null);

        pane.getChildren().add(rectangle);
    }

    @Override
    public String toString() {
        return String.format("Upper left corner coords %s, Lower right corner %s", super.toString(), lPoint);
    }
}

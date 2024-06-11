package shape;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;
// Abbreviaton key:       fx-drawing-main
// Template description: JavaFX App class for drawing
// Variables: CLASS_NAME must be assigned clipboard() expression
// 1. Create a Java class
// 2. Copy the class name in the Clipboard (^C)
// 3, Overwrite all  the class contents by running this Live template
// 4. Right-click the class name (should be the same as in the originally created class)
// 5. Select Show Content actions and execute Set package name to ...<your package name>

public class Geometry extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 250);

        double width = scene.getWidth();
        double height = scene.getHeight();

        double radius = Math.min(width, height) / 3;

        double centerX = width / 2;
        double centerY = height / 2;

        Circle circle = new Circle(centerX, centerY, radius);

        circle.setFill(null);
        circle.setStroke(Color.BLUE);

        // add circle to group in order to be drawn
        group.getChildren().add(circle);

        double lineX = getAndValidateLineX(width);

        Line line = new Line(lineX, 0, lineX, height);
        line.setStroke(Color.RED);
        group.getChildren().add(line);

        double chSquared = radius * radius - (centerX - lineX) * (centerX - lineX);

        if (chSquared >= 0) {
            double intersectionOneY = centerY + Math.sqrt(chSquared);
            drawIntersectionPoint(group, lineX, intersectionOneY);

            if (chSquared > 0) {
                double intersectionTwoY = centerY - Math.sqrt(chSquared);
                drawIntersectionPoint(group, lineX, intersectionTwoY);
            }
        } else {
            showWarningDialog();
            Platform.exit();
        }

        stage.setTitle("Geometry");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    private void drawIntersectionPoint(Group group, double pointX, double pointY) {
        Circle circle = new Circle(pointX, pointY, 3);

        Text text = new Text(pointX + 5, pointY, String.format("(%.2f; %.2f)", pointX, pointY));

        group.getChildren().addAll(circle, text);
    }

    private void showWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("The are no intersections.");

        alert.showAndWait();
    }

    private double getLineX() {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Prompt");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter x:");

        Optional<String> input = dialog.showAndWait();

        return Double.parseDouble(input.get());
    }

    private double getAndValidateLineX(double width) {
        double lineX;

        do {
            lineX = getLineX();

            if (lineX < 0 || lineX > width) {
                showErrorDialog(width);
            }
        } while (lineX < 0 || lineX > width);

        return  lineX;
    }

    private void showErrorDialog(double width) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        String message = String.format("X should be within bounds: [0; %.2f]", width);
        alert.setContentText(message);

        alert.showAndWait();
    }
}

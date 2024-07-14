package draw;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
// Abbreviaton key:       fx-drawing-main
// Template description: JavaFX App class for drawing
// Variables: CLASS_NAME must be assigned clipboard() expression
// 1. Create a Java class
// 2. Copy the class name in the Clipboard (^C)
// 3, Overwrite all  the class contents by running this Live template
// 4. Right-click the class name (should be the same as in the originally created class)
// 5. Select Show Content actions and execute Set package name to ...<your package name>

public class DrawingApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 450, 350);

        double width = scene.getWidth(); // total width of the scene
        double height = scene.getHeight(); // total height of the scene

        // coordinates of the center of the scene
        double centerX = width / 2;
        double centerY = height / 2;

        double length = 25;

        int spinCount = 5;  // count of the spins

        for (int i = 0; i < spinCount; i++) {

            // for right line of the spiral
            Line rightLine = new Line(centerX + (i * length), centerY - (i * length), centerX + (i * length), centerY + (i + 1) * length);
            rightLine.setStroke(Color.ORANGE);
            rightLine.setStrokeWidth(2);

            // for bottom line of the spiral
            Line bottomLine = new Line(centerX + (i * length), centerY + (i + 1) * length, centerX - (i + 1) * length, centerY + (i + 1) * length);
            bottomLine.setStroke(Color.ORANGE);
            bottomLine.setStrokeWidth(2);

            // for left line of the spiral
            Line leftLine = new Line(centerX - (i + 1) * length, centerY + (i + 1) * length, centerX - (i + 1) * length, centerY - (i + 1) * length);
            leftLine.setStroke(Color.ORANGE);
            leftLine.setStrokeWidth(2);

            // for upper line of the spiral
            Line upperLine = new Line(centerX - (i + 1) * length, centerY - (i + 1) * length, centerX + (i + 1) * length, centerY - (i + 1) * length);
            upperLine.setStroke(Color.ORANGE);
            upperLine.setStrokeWidth(2);

            // add all lines to group to be drawn
            group.getChildren().addAll(rightLine, bottomLine, leftLine, upperLine);
        }

        stage.setTitle("Problem 2: Square Spiral");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
}

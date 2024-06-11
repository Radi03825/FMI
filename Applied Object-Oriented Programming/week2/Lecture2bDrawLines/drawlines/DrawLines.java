/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawlines;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class DrawLines extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group group = new Group(); // Create a layout Panel
        Scene scene = new Scene(group, 500, 500);// Create the Scene

        // add content to the Layout panel
        double width = scene.getWidth(); // total width of the scene 
        double height = scene.getHeight(); // total width of the scene

        int radius = 0;

        for (int i = 0; i < 12; i++) {
            radius += 10;

            // add circle
            Circle circle = new Circle(width / 2, height / 2, radius);

            circle.setFill(Color.TRANSPARENT);
            circle.setStroke(Color.BLUE);
            circle.setStrokeWidth(2);

            group.getChildren().add(circle);
        }

        // add line from the top of the last circle to down of it
        Line verticalDiameter = new Line(width / 2, height / 2 - radius, width / 2, height / 2 + radius);
        verticalDiameter.setStroke(Color.RED);
        group.getChildren().add(verticalDiameter);

        // add line from the left of the last circle to the right
        Line horizontalDiameter = new Line(width / 2 - radius, height / 2, width / 2 + radius, height / 2);
        horizontalDiameter.setStroke(Color.RED);
        group.getChildren().add(horizontalDiameter);

        // add the center of the circles
        Circle center = new Circle(width / 2, height / 2, 3);
        center.setFill(Color.BLACK);
        group.getChildren().add(center);


        /*
        // draw a line from the upper-left to the lower-right
        Line line = new Line(0, 0, width, height);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);
        group.getChildren().add(line);
        // draw a line from the lower-left to the upper-right
        line = new Line(0, height, width, 0);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(2);
        group.getChildren().add(line);
        */


        // Set the title of the Stage(the application window)
        primaryStage.setTitle("Drawing shapes");
        // Add the Scene to the Stage
        primaryStage.setScene(scene);
        // Show the Stage (the application window)
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

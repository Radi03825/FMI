/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawshapeswithdialog;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DrawShapesWithDialog extends Application {

    public static String inputDialog(String infoMessage, String titleBar,
                                                         String headerMessage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titleBar);
        dialog.setHeaderText(headerMessage);
        dialog.setContentText(infoMessage);
        return dialog.showAndWait().get();
    }
    
    @Override
    public void start(Stage primaryStage) {
        // obtain user's choice
        String input = inputDialog("Enter 1 to draw rectangles\n"  +
                                   "Enter 2 to draw ovals", 
                                   "User choice", "Select a shape");

        int choice = Integer.parseInt(input); // convert input to int
        Rectangle rectangle; //shape
        Circle circle;       //shape
        Group group = new Group(); // Create a layout Panel
        Scene scene = new Scene(group, 400, 400);// Create the Scene      
        // create the shape nodes
        for (int i = 0; i < 10; i++) {
            // pick the shape based on the user's choice
            switch (choice) {
                case 1: // draw rectangles
                    rectangle = new Rectangle(10 + i * 20, 10 + i * 20,
                            40 + i * 10, 40 + i * 10);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setFill(null);
                    group.getChildren().add(rectangle);
                    break;
                case 2: // draw ovals
                    circle = new Circle(50 + i * 20, 50 + i * 20,
                            40 + i * 10);
                    circle.setStroke(Color.BLACK);
                    circle.setFill(null);
                     group.getChildren().add(circle);
                    break;
            } // end switch
        } // end for

 
        primaryStage.setTitle("Drawing shapes in a loop");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

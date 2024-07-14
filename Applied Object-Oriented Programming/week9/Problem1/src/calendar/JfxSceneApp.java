package calendar;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
// Abbreviation key:       fx-drawing-main
// Template description: JavaFX App class for drawing
// Variables: CLASS_NAME must be assigned clipboard() expression
// 1. Create a Java class
// 2. Copy the class name in the Clipboard (^C)
// 3, Overwrite all  the class contents by running this Live template
// 4. Right-click the class name (should be the same as in the originally created class)
// 5. Select Show Content actions and execute Set package name to ...<your package name>

public class JfxSceneApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group,  350, 200);

        double width = scene.getWidth(); // total width of the scene
        double height = scene.getHeight(); // total height of the scene

        int year = getYearAndValidate(); // get validated year
        int month = getMonthAndValidate(); // get validated month

        LocalDate date = LocalDate.of(year, month, 1);

        Month dateMonth = date.getMonth();
        int dateYear = date.getYear();

        double startX = width / 6;
        double startY = height / 5;

        String daysOfWeek[] = {"MON", "TUE", "WED", "THR", "FRI", "SAT", "SUN"};

        String week = "";

        // create week content for weekText
        for (int i = 0; i < daysOfWeek.length; i++) {
            week += daysOfWeek[i] + "     ";
        }

        Text weekText = new Text(startX, startY, week);
        weekText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 11));

        group.getChildren().add(weekText); // add weekText to group

        int dayOfWeekAsValue = date.getDayOfWeek().getValue();

        int weekCount = (int) Math.ceil((date.lengthOfMonth() + dayOfWeekAsValue - 1) / 7.0); // calculate week count

        double currentX = startX;
        double currentY = startY + 15;

        double distanceBetweenDates = weekText.getLayoutBounds().getWidth() / daysOfWeek.length;

        while (weekCount > 0) {
            for (int i = 1; i <= 7 && month == date.getMonth().getValue(); i++) {
                Text currentDate = new Text();
                currentDate.setX(currentX);
                currentDate.setY(currentY);

                currentX += distanceBetweenDates + 1;

                if (date.getDayOfWeek().getValue() != i) {
                    currentDate.setText(" ");
                } else {
                    currentDate.setText(String.valueOf(date.getDayOfMonth())); // set current date

                    // check if this is the current date
                    if (date.equals(LocalDate.now())) {
                        currentDate.setFill(Color.RED);
                        currentDate.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                    }

                    date = date.plusDays(1);
                }

                group.getChildren().add(currentDate); // add current date to group
            }

            currentY += 15;
            currentX = startX;

            weekCount--;
        }

        stage.setTitle("Calendar: " + dateMonth + " " + dateYear);
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    private int getMonthAndValidate() {
        int month;

        do {
            month = getMonth();

            if (month < 1 || month > 12) {
                showErrorDialog("Month should be between 1 and 12."); // show error until month is correct
            }

        } while (month < 1 || month > 12);

        return month;
    }

    private int getYearAndValidate() {
        int year;

        do {
            year = getYear();

            if (year <= 0) {
                showErrorDialog("Year should be positive number."); // show error until year is correct
            }

        } while (year <= 0);

        return year;
    }

    private int getMonth() {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Prompt");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter month:");

        Optional<String> input = dialog.showAndWait();

        // check for empty input
        if (input.get().isEmpty()) {
            return -1;  // return invalid value for month
        }

        return Integer.parseInt(input.get());
    }

    private int getYear() {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Prompt");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter year:");

        Optional<String> input = dialog.showAndWait();

        // check for empty input
        if (input.get().isEmpty()) {
            return -1; // return invalid value for year
        }

        return Integer.parseInt(input.get());
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}

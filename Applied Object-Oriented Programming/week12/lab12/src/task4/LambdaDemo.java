package task4;

import javafx.scene.control.Button;

public class LambdaDemo {
    public static void main(String[] args) {
        Button button = new Button();

//        button.setOnAction(
//                new EventHandler<ActionEvent>()
//                {
//                    public void handle(ActionEvent event)
//                    {
//                        System.out.println("Button clicked.");
//                    }
//                }
//        );

        button.setOnAction(actionEvent -> System.out.println("Button clicked."));
    }
}

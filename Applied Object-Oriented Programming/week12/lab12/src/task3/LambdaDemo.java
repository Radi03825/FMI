package task3;

import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public class LambdaDemo {
    public static void main(String[] args) {

        // a)
        IntConsumer consumer = new IntConsumer() {
            public void accept(int value) {
                System.out.printf("%d ", value);
            }
        };

        IntConsumer consumerLambda = value -> System.out.printf("%d ", value);


        // b)
        ToUpperCase toUpperCase = (String s) -> {return s.toUpperCase();};
        ToUpperCase toUpperCaseAnon = new ToUpperCase() {
            @Override
            public String toUpperCase(String text) {
                return text.toUpperCase();
            }
        };


        // c)
        Supplier<String> welcomeLambda = () -> "Welcome to lambdas!";

        // d)
        IntBinaryOperator max = (num1, num2) -> num1 > num2 ? num1 : num2; //Math.max(num1, num2);
    }
}

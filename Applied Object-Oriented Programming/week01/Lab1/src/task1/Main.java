package task1;

import java.util.Scanner;

import static java.util.FormatProcessor.FMT;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //a)
        System.out.print("Enter fahrenheit: ");
        double fahrenheit = input.nextDouble();

        System.out.printf("Celsius: %.2f%n", fahrenheitToCelsius(fahrenheit));

        //b)
        System.out.print("Enter celsius: ");
        double celsius = input.nextDouble();

        //System.out.printf("Fahrenheit: %.2f%n", celsiusToFahrenheit(celsius));

        String message = FMT."Fahrenheit: %.2f\{celsiusToFahrenheit(celsius)}";
        System.out.println(message);



        //c)
        String menu = """
                Menu: 
                1. Fahrenheit to Celsius
                2. Celsius to Fahrenheit
                Choose an option:
                """;

        System.out.print(menu);

        int choice = input.nextInt();

        if (choice == 1) {
            System.out.print("Enter fahrenheit: ");
            double fahrenheitInner = input.nextDouble();

            System.out.printf("Celsius: %.2f%n", fahrenheitToCelsius(fahrenheitInner));
        }  else if (choice == 2) {
            System.out.print("Enter celsius: ");
            double celsiusInner = input.nextDouble();

            String output = FMT."Fahrenheit: %.2f\{celsiusToFahrenheit(celsiusInner)}";
            System.out.print(output);
        } else {
            System.out.println("Incorrect option.");
        }
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return 5.0 / 9.0 * ( fahrenheit - 32 );
    }

    public static double celsiusToFahrenheit(double celsius) {
        return 9.0 / 5.0 * celsius + 32;
    }
}
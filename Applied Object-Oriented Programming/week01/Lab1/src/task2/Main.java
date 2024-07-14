package task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter five-digit number: ");
        int number = input.nextInt();

        if (!checkIfNumberIsFiveDigit(number)) {
            System.out.println(STR."\{number} is not a five-digit number.");
        } else {
            int originalNumber = number;

            int fifthDigit = number % 10;
            number /= 10;

            int fourthDigit = number % 10;
            number /= 10;

            int thirdDigit = number % 10;
            number /= 10;

            int secondDigit = number % 10;
            number /= 10;

            int firstDigit = number;

            if (firstDigit == fifthDigit && secondDigit == fourthDigit) {
                System.out.println(STR."\{originalNumber} is a polindrome.");
            } else {
                System.out.println(STR."\{originalNumber} is not a palindrome.");
            }
        }

    }

    private static boolean checkIfNumberIsFiveDigit(int number) {
        return (String.valueOf(number).length() == 5);
    }
}

package task3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter four-digit number: ");
        int number = input.nextInt();

        if (!checkIfNumberIsFourDigit(number)) {
            System.out.println(STR."\{number} is not a four-digit number.");
        } else {
            printEncryptedNumber(number);
        }

        System.out.print("Enter four-digit number: ");
        int decryptNumber = input.nextInt();

        //System.out.println(decryptNumber(decryptNumber));
        printDecryptNumber(decryptNumber);
    }

    private static void printDecryptNumber(int number) {
        int fourthDigit = number % 10;
        number /= 10;

        int thirdDigit = number % 10;
        number /= 10;

        int secondDigit = number % 10;
        number /= 10;

        int firstDigit = number;

        firstDigit = getOriginalNumber(firstDigit);
        secondDigit = getOriginalNumber(secondDigit);
        thirdDigit = getOriginalNumber(thirdDigit);
        fourthDigit = getOriginalNumber(fourthDigit);

        System.out.print("Decrypted number: ");
        System.out.printf("%d%d%d%d%n", thirdDigit, fourthDigit, firstDigit, secondDigit);
    }

    private static int getOriginalNumber(int number) {
        return (number + 10 - 7) % 10;
    }

    private static void printEncryptedNumber(int number) {
        int fourthDigit = number % 10;
        number /= 10;

        int thirdDigit = number % 10;
        number /= 10;

        int secondDigit = number % 10;
        number /= 10;

        int firstDigit = number;

        firstDigit = replaceDigit(firstDigit);
        secondDigit = replaceDigit(secondDigit);
        thirdDigit = replaceDigit(thirdDigit);
        fourthDigit = replaceDigit(fourthDigit);

        System.out.print("Encrypted number: ");
        System.out.printf("%d%d%d%d%n", thirdDigit, fourthDigit, firstDigit, secondDigit);
    }

    private static int replaceDigit(int digit) {
        return (digit + 7) % 10;
    }

    private static boolean checkIfNumberIsFourDigit(int number) {
        return (String.valueOf(number).length() == 4);
    }

}

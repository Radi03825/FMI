package date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        int year = random.nextInt(1900, 2021);

        LocalDate date = LocalDate.of(year, 1, 13);

        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println(date);
            }

            date = date.plusMonths(1);
        }
    }
}
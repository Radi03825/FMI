package test;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        Random random = new Random();

        System.out.printf("%-10s%-10s%n", "Face", "Frequency");

        IntStream.range(0, 6_000_000)
                .mapToObj(i -> random.nextInt(1, 7))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%-10d%-10d%n", entry.getKey(), entry.getValue()));
    }
}

package test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLetters {
    public static void main(String[] args) {
        Random random = new Random();

        List<Character> list = IntStream.range(0, 30)
                .mapToObj(i -> (char) ('A' + random.nextInt(24)))
                .collect(Collectors.toList());

        System.out.println("Original: " + list);

        // a)
        List<Character> sorted = list.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted: " + sorted);

        // b)
        sorted = list.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Sorted reversed: " + sorted);

        // c)
        System.out.print("Sorted and removed duplicates: ");
        list.stream()
                .sorted()
                .distinct()
                .forEach(symbol -> System.out.printf("%c, ", symbol));

        // d)
        list.stream()
                .collect(Collectors.groupingBy(symbol -> symbol, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%c -> %d%n", entry.getKey(), entry.getValue()));
    }
}
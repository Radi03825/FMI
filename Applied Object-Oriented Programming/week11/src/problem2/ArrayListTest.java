package problem2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> aList = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h"));

        ArrayList<String> bList = new ArrayList<>(Arrays.asList("a", "c", "e", "x", "y", "a"));

        System.out.println("Common elements: ");
        Iterator<String> aIterator = aList.iterator();
        Iterator<String> bIterator;

        while (aIterator.hasNext()) {
            String aElement = aIterator.next();

            bIterator = bList.iterator();

            while (bIterator.hasNext()) {
                if (aElement.equals(bIterator.next())) {
                    System.out.println(aElement);
                    break;
                }
            }
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList("Лили", "Мария", "Георги", "Илия", "Цвета", "Георги"));

        ListIterator<String> nameIterator = list.listIterator();

        while (nameIterator.hasNext()) {
            if (nameIterator.next().equals("Георги")) {
                list.add(nameIterator.nextIndex(), "Симеон");
                break;
            }
        }

        System.out.println(list);
    }
}

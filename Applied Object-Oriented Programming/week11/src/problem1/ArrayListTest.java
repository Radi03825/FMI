package problem1;

import java.util.*;

public class ArrayListTest {
    //a)
    public static <E extends Comparable<E>> E max(E[][] list) {
        E max = list[0][0];

        for (int i = 0; i < list.length; i++) {
            List<E> row = Arrays.asList(list[i]);

            if (max.compareTo(Collections.max(row)) < 0) {
                max = Collections.max(row);
            }
        }

        return max;
    }

    //b)
    public static <E> void shuffle(ArrayList<E> list) {
        Collections.shuffle(list);
    }

    //c)
    public static <E extends Comparable<E>> E max(ArrayList<E> list) {
        return Collections.max(list);
    }

    //d)
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> noDuplicates = new ArrayList<>();

        for (E e : list) {
            if (Collections.frequency(noDuplicates, e) == 0) {
                noDuplicates.add(e);
            }

//            if (!noDuplicates.contains(e)) {
//                noDuplicates.add(e);
//            }
        }

//        LinkedHashSet<E> set = new LinkedHashSet<>(list);
//        noDuplicates.addAll(set);

        return noDuplicates;
    }

    public static void main(String[] args) {
        Integer[][] arr = new Integer[][] {
                {1, 2, 3},
                {4, 15, 6},
                {7, 8, 9}
        };

        System.out.println("Max: " + max(arr));

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 14, 5, 6, 7, 8, 9, 4, 4, 7, 3, 7));

        System.out.println("Max: " + max(list));

        shuffle(list);

        System.out.println("List: " + list);

        ArrayList<Integer> removedDuplicates = removeDuplicates(list);

        System.out.println("No duplicates: " + removedDuplicates);
    }
}

import java.util.*;

class WriteText{
    public static void writeAllLines(List<? extends Number> data, String destFile) throws Exception {
        try (var out = new Formatter( destFile )) {
            var iterator = data.listIterator();
            while(iterator.hasNext()){
                out.format("%s%n", iterator.next());
            }
        }
    }
    public static void main(String... args ) throws Throwable {
        writeAllLines(Arrays.asList(1, 2, 3, 4), "ints.txt");
        writeAllLines(Arrays.asList(1., 2., 3., 4.), "dbls.txt");
        writeAllLines(Arrays.asList(1., 2, 3, 4.), "mixed.txt");

        Comparator.comparing((String s) -> s.charAt(0)).reversed();
    }
}

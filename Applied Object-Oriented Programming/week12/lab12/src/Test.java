import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        var list3 = Arrays.asList("Mary", "had", "a", "little", "lamb");
        Set<String> set3 = new HashSet<>(list3);
        set3.addAll(list3);
        var iterator = set3.iterator();
        while (iterator.hasNext())
        {
            var next = iterator.next();
            if (next.length() > 1)
                set3.remove(next);
        }
        System.out.println(set3);



        var list = new ArrayList<Integer>();
        list.add(7);
        list.add(4);
        list.add(7);
        var set = new TreeSet<Integer>((e1,e2)-> e2- e1);
        set.addAll(list);
        System.out.print(set.size());
        System.out.print(" ");
        System.out.print(set.iterator().next());




        var set1 = new HashSet<String>();

        //Map.Entry<String, Integer> entry = new Hashtable<String, Integer>().entrySet();

        Collection<Integer> ints = new ArrayList<>();

        Set<Map.Entry<String, Integer>> entry = new Hashtable<String, Integer>().entrySet();

        List<String> intss = new Vector<>();


        Set<Integer> intsset = new HashSet<>();

        try (var os = new ObjectOutputStream(new FileOutputStream("x"))) {

        }

        var list1 = List.of("Austin", "Boston", "San Francisco");
        var c = list1.stream()
                .filter(a -> a.length() > 10) // line X
                .count();
        System.out.println(c + " " + list.size());


        var list2 = new LinkedList<String>();
        list2.add("Women");
        list2.add("Men");
        //list2.stream().forEach(System.out.println);
        //list2.stream().forEach(System.out.println);


        var lsl = Arrays.asList("1"); //List.of(); //Arrays.asList(new ArrayList<>());


        Comparator<String> c1 = (j, k) -> 0;
        Comparator<String> c2 = (String j, String k) -> 0;
       // Comparator<String> c3 = (var j, String k) -> 0;
       // Comparator<String> c4 = (var j, k) -> 0;
        Comparator<String> c5 = (var j, var k) -> 0;



    }


}

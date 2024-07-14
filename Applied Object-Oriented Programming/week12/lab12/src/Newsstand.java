import java.util.TreeSet;

record Magazine(String name, int volume) implements Comparable<Magazine> {
    public int compareTo(Magazine m) {
        return name.compareTo(m.name);
    }
}
class Newsstand {
    public static void main(String[] args) {
        var set = new TreeSet<Magazine>((e1, e2)-> e1.volume()- e2.volume());
        set.add(new Magazine("highlights", 3));
        set.add(new Magazine("Newsweek", 1));
        set.add(new Magazine("highlights", 2));
        //System.out.println(set.listIterator().next());
    }
}

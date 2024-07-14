package problem6;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {

    public static void main(String[] args) {
        TreeMap<String, String> teams = new TreeMap<>();

        teams.put("San Francisco", "Forty-niners");
        teams.put("Chicago", "Bears");
        teams.put("Denver", "Broncos");
        teams.put("Seatlle", "Seahawks");
        teams.put("Miami", "Dolphins");
        teams.put("Detroit", "Lions");

//        System.out.println(teams);

        //a)
        System.out.println("Number of teams: " + teams.size());
        System.out.println("Chicago teams: " + teams.get("Chicago"));

        //b)
        teams.put("San Francisco", "Niners");
        System.out.println(teams);

        //c)
        if (teams.containsKey("San Diego")) {
            System.out.println("San Diego has a team.");
        } else {
            System.out.println("San Diego doesn't have a team.");
        }

        //d)
        teams.remove("Denver");
        System.out.println(teams);

        //e) and f)
        teams.put("Dallas", "Cowboys");

        for (Map.Entry<String, String> t : teams.entrySet()) {
            System.out.printf("%-20s%-20s\n", t.getKey(), t.getValue());
        }

        //g)
        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(teams.entrySet());

        //list.sort(Map.Entry.comparingByValue()); // ASCENDING
        list.sort(Map.Entry.<String, String>comparingByValue().reversed()); // DESCENDING

        System.out.println(list);
    }
}

import java.util.TreeSet;

public class BestPlayers
{
    record FootballPlayer(String name, int goalsScored)
            implements Comparable<FootballPlayer> {
        @Override
        public int compareTo(FootballPlayer anotherPlayer) {
            return this.goalsScored - anotherPlayer.goalsScored;
        }
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            FootballPlayer player = (FootballPlayer) object;
            return name.equals(player.name);
        }
    };
    public static void main(String[] args) {
        FootballPlayer messi = new FootballPlayer("Messi", 800);
        FootballPlayer ronaldo = new FootballPlayer("Ronaldo", 800);
        TreeSet<FootballPlayer> set = new TreeSet<>();
        set.add(ronaldo);
        set.add(messi);

        System.out.println(set.size());
        System.out.println(set.iterator().next());
    }
}

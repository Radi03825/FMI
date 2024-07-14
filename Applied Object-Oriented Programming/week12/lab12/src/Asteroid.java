import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

class Asteroid {
    public void mine(BiFunction<Integer,Double,Double> lambda) {
        // IMPLEMENTATION OMITTED


    }
    public static void main(String[] debris) {
        new Asteroid().mine((s, p) -> s + p);
    }
}
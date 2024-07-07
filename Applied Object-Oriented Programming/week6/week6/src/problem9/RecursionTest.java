package problem9;

public class RecursionTest {

    public static double sum(double s, int i) {
        if (i == 0) {
            return s;
        }

        return sum(s + (double) i / (2 * i + 1), i - 1);
    }

    public static void main(String[] args) {
        System.out.println(sum(0, 3));
    }
}

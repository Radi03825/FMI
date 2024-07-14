import java.util.Arrays;
import java.util.Collections;

public class Animal {
    public void printNewAge(int age) {
        String message = "The age is ";
        class PrintUtility {
            void print() {
                //System.out.println(message + (++age)); // Line 1
            }
        }
        PrintUtility utility = new PrintUtility();
        utility.print(); // Line 2
    }
    public static void main(String[] args) {
        new Animal().printNewAge(10);


        var array = new String[]{"B", "A", "C"};
        var threes = Arrays.asList(array);
        Collections.sort(threes, (s1, s2)-> s2.compareTo(s1));
        System.out.println(Arrays.toString(array));
    }
}
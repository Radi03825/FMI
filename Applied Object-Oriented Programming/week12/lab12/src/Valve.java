import java.io.*;
import java.util.Arrays;

public class Valve implements Serializable {
    private int chambers ;
    private Double size ;
    private static String color = "BLUE";
    public Valve( ) {
        this.chambers = 0;
        size = 0.0;
    }
    @Override
    public String toString() { return chambers + ", " + size + ", " + color; }
    public static void main(String... args) throws Throwable {
        Valve[] valves = new Valve[2];
        try (var os = new ObjectOutputStream( new FileOutputStream("scan.ser"))) {
            final Valve v = new Valve();
            for (int i = 0; i < valves.length; i++) {
                v.chambers = 2 * i;
                v.size = 10.0 * i;
                v.color = "RED-" + i;
                valves[i] = v;
            }
            os.writeObject(valves);
        }
        try (var is = new ObjectInputStream( new FileInputStream("scan.ser"))) {
            valves = (Valve[]) is.readObject();
            System.out.print(Arrays.toString(valves));
        }
    }
}
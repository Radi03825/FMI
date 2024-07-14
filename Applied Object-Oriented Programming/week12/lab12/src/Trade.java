import java.io.*;
import java.util.Arrays;

public class Trade implements Serializable {
    private double[] prices;
    public Trade( ) { }
    @Override
    public String toString() {
        return String.format("{%s}", Arrays.toString(prices));
    }
    public static void main(String... args) throws Throwable {
        Trade[] trades = new Trade[2];
        try (var os= new ObjectOutputStream( new FileOutputStream("scan1.ser"))) {
            for (int i = 0; i < trades.length; i++) {
                final Trade trade = new Trade();
                Arrays.fill(trade.prices, 100 * i);
                trades[i] = trade;
            }
            os.writeObject(trades);
        }
        try (var is = new ObjectInputStream( new FileInputStream("scan1.ser"))) {
            trades = (Trade[]) is.readObject();
            System.out.print(Arrays.toString(trades));
        }
    }
}

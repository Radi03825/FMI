package comStock;

public class StockTest {
    public static void main(String[] args) {

        Stock stock = new Stock("1", "First", 10, 15); // create stock

        System.out.printf("Stock symbol: %s%n", stock.getSymbol()); // test get symbol
        System.out.printf("Stock name: %s%n", stock.getName()); // test get name
        System.out.printf("Stock previous closing price: %.2f%n", stock.getPreviousClosingPrice()); // test get previous closing price
        System.out.printf("Stock current price: %.2f%n", stock.getCurrentPrice()); // test get current price
        System.out.printf("Stock change percent: %.2f%%%n", stock.changePercent()); // test change percent
    }
}

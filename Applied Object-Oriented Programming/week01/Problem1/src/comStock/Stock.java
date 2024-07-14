package comStock;

public class Stock {
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    public Stock() {
        this("Default", "Default", 1, 1); // set to default values
    }

    public Stock(String symbol, String name, double previousClosingPrice, double currentPrice) {
        setSymbol(symbol);
        setName(name);
        setPreviousClosingPrice(previousClosingPrice);
        setCurrentPrice(currentPrice);
    }

    public Stock(Stock stock) {
        this(stock.symbol, stock.name, stock.previousClosingPrice, stock.currentPrice);
    }

    public double changePercent() {
        double change = currentPrice - previousClosingPrice; // calculate the difference between current and previous stock price
        double changePercent = (change / previousClosingPrice) * 100; // calculate the change percent

        return changePercent;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPreviousClosingPrice() {
        return previousClosingPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setSymbol(String symbol) {
        if (symbol != null) {
            this.symbol = symbol;
        } else {
            this.symbol = "Default"; // set to default value
        }
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "Default"; // set to default value
        }
    }

    public void setPreviousClosingPrice(double price) {
        if (price > 0) {
            this.previousClosingPrice = price;
        } else {
            this.previousClosingPrice = 1; // set to default value
        }
    }

    public void setCurrentPrice(double price) {
        if (price > 0) {
            this.currentPrice = price;
        } else {
            this.currentPrice = 1; // set to default value
        }
    }
}

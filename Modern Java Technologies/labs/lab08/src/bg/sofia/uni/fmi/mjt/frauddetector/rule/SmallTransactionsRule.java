package bg.sofia.uni.fmi.mjt.frauddetector.rule;

import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Transaction;

import java.util.List;

public class SmallTransactionsRule extends BaseRule {

    private final int countThreshold;
    private final double amountThreshold;

    public SmallTransactionsRule(int countThreshold, double amountThreshold, double weight) {
        super(weight);
        this.countThreshold = countThreshold;
        this.amountThreshold = amountThreshold;
    }

    @Override
    public boolean applicable(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty() || transactions.size() < this.countThreshold) {
            return false;
        }

        long smallTransactionsCount = transactions.stream()
            .filter(transaction -> transaction.transactionAmount() <= this.amountThreshold)
            .count();

        return smallTransactionsCount >= this.countThreshold;
    }

}

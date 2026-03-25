package bg.sofia.uni.fmi.mjt.frauddetector.rule;

import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Transaction;

import java.util.List;

public class ZScoreRule extends BaseRule {

    private final double zScoreThreshold;

    public ZScoreRule(double zScoreThreshold, double weight) {
        super(weight);
        this.zScoreThreshold = zScoreThreshold;
    }

    @Override
    public boolean applicable(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return false;
        }

        double averageAmount = transactions.stream()
            .mapToDouble(Transaction::transactionAmount)
            .average()
            .orElse(0);

        double variance = transactions.stream()
            .mapToDouble(transaction -> Math.pow(transaction.transactionAmount() - averageAmount, 2))
            .average()
            .orElse(0);

        double standardDeviation = Math.sqrt(variance);
        if (standardDeviation == 0) {
            return false;
        }

        return transactions.stream()
            .mapToDouble(transaction -> (transaction.transactionAmount() - averageAmount) / standardDeviation)
            .anyMatch(zScore -> zScore > zScoreThreshold);
    }

}

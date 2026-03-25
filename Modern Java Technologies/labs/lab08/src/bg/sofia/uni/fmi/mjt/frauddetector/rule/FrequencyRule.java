package bg.sofia.uni.fmi.mjt.frauddetector.rule;

import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Transaction;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.List;

public class FrequencyRule extends BaseRule {

    private final int transactionCountThreshold;
    private final TemporalAmount timeWindow;

    public FrequencyRule(int transactionCountThreshold, TemporalAmount timeWindow, double weight) {
        super(weight);
        this.transactionCountThreshold = transactionCountThreshold;
        this.timeWindow = timeWindow;
    }

    @Override
    public boolean applicable(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty() || transactions.size() < transactionCountThreshold) {
            return false;
        }

        return transactions.stream()
            .anyMatch(transaction -> {
                LocalDateTime startTime = transaction.transactionDate();
                LocalDateTime endTime = startTime.plus(timeWindow);

                long count = getCount(transactions, startTime, endTime);

                return count >= transactionCountThreshold;
            });
    }

    private static long getCount(List<Transaction> transactions, LocalDateTime startTime, LocalDateTime endTime) {
        return transactions.stream()
            .filter(t -> !t.transactionDate().isBefore(startTime) && !t.transactionDate().isAfter(endTime))
            .count();
    }

}

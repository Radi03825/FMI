package bg.sofia.uni.fmi.mjt.frauddetector.rule;

import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Transaction;

import java.util.List;

public class LocationsRule extends BaseRule {

    private final int threshold;

    public LocationsRule(int threshold, double weight) {
        super(weight);
        this.threshold = threshold;
    }

    @Override
    public boolean applicable(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty() || transactions.size() < this.threshold) {
            return false;
        }

        long differentLocationsCount = transactions.stream()
            .map(Transaction::location)
            .distinct()
            .count();

        return differentLocationsCount >= this.threshold;
    }

}

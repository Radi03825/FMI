package bg.sofia.uni.fmi.mjt.frauddetector.analyzer;

import bg.sofia.uni.fmi.mjt.frauddetector.rule.Rule;
import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Channel;
import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TransactionAnalyzerImpl implements TransactionAnalyzer {
    private Map<String, List<Transaction>> transactions;
    private List<Rule> rules;

    public TransactionAnalyzerImpl(Reader reader, List<Rule> rules) {
        this.transactions = new HashMap<>();

        checkRulesRisks(rules);
        this.rules = rules;

        loadTransactions(reader);
    }

    @Override
    public List<Transaction> allTransactions() {
        return this.transactions.values().stream()
            .flatMap(List::stream)
            .toList();
    }

    @Override
    public List<String> allAccountIDs() {
        return this.transactions.keySet().stream()
            .toList();
    }

    @Override
    public Map<Channel, Integer> transactionCountByChannel() {
        return this.transactions.values().stream()
            .flatMap(List::stream)
            .collect(Collectors.groupingBy(
                Transaction::channel,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue))
            );
    }

    @Override
    public double amountSpentByUser(String accountID) {
        List<Transaction> transactionsByUser = this.allTransactionsByUser(accountID);
        return transactionsByUser.stream()
            .mapToDouble(Transaction::transactionAmount)
            .sum();
    }

    @Override
    public List<Transaction> allTransactionsByUser(String accountId) {
        if (accountId == null || accountId.isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty");
        }

        List<Transaction> transactionsByUser = this.transactions.get(accountId);
        if (transactionsByUser == null) {
            return List.of();
        }

        return transactionsByUser;
    }

    @Override
    public double accountRating(String accountId) {
        List<Transaction> userTransaction = this.allTransactionsByUser(accountId);
        if (userTransaction.isEmpty()) {
            return 0;
        }

        double accountRiskRating = this.rules.stream()
            .filter(rule -> rule.applicable(userTransaction))
            .mapToDouble(Rule::weight)
            .sum();

        if (accountRiskRating < 0 || accountRiskRating > 1) {
            throw new IllegalArgumentException("Account risk rating must be between 0 and 1");
        }

        return accountRiskRating;

    }

    @Override
    public SortedMap<String, Double> accountsRisk() {
        Map<String, Double> accounts = this.transactions.keySet().stream()
            .collect(Collectors.toMap(
                accountId -> accountId,
                this::accountRating
            ));

        TreeMap<String, Double> sortedResultTreeMap = new TreeMap<>((f, s) -> {
            int comparedByRating = Double.compare(accountRating(s), accountRating(f));
            if (comparedByRating != 0) {
                return comparedByRating;
            }

            return f.compareTo(s);
        });

        sortedResultTreeMap.putAll(accounts);

        return sortedResultTreeMap;
    }

    private void checkRulesRisks(List<Rule> rules) {
        double sum = rules.stream().mapToDouble(Rule::weight).sum();

        if (sum != 1) {
            throw new IllegalArgumentException("Sum of rules risks must be 1");
        }
    }

    private void loadTransactions(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("Reader cannot be null");
        }

        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            this.transactions = bufferedReader.lines()
                .skip(1)
                .map(Transaction::of)
                .collect(Collectors.groupingBy(Transaction::accountID));
        } catch (IOException ex) {
            throw new IllegalArgumentException("Error while reading transactions", ex);
        }
    }
    
}

package bg.sofia.uni.fmi.mjt.frauddetector.analyzer;

import bg.sofia.uni.fmi.mjt.frauddetector.rule.FrequencyRule;
import bg.sofia.uni.fmi.mjt.frauddetector.rule.LocationsRule;
import bg.sofia.uni.fmi.mjt.frauddetector.rule.Rule;
import bg.sofia.uni.fmi.mjt.frauddetector.rule.SmallTransactionsRule;
import bg.sofia.uni.fmi.mjt.frauddetector.rule.ZScoreRule;
import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Channel;
import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionAnalyzerImplTest {
    private TransactionAnalyzerImpl analyzer;
    private List<Rule> rules;

    @BeforeEach
    public void setUp() {
        String transactionsData = """
            TransactionID,AccountID,TransactionAmount,TransactionDate,Location,Channel
            TX000001,AC00128,10.00,2023-04-11 16:29:14,San Diego,ATM
            TX000002,AC00455,20.00,2023-06-27 16:44:19,Houston,ATM
            TX000402,AC00128,30.00,2023-03-29 18:12:17,Sacramento,Online
            """;
        Reader reader = new StringReader(transactionsData);
        rules = List.of(
            new ZScoreRule(1.5, 0.3),
            new LocationsRule(3, 0.4),
            new FrequencyRule(4, Period.ofWeeks(4), 0.25),
            new SmallTransactionsRule(1, 10.20, 0.05)
        );
        analyzer = new TransactionAnalyzerImpl(reader, rules);
    }

    @Test
    public void testAllTransactions() {
        List<Transaction> transactions = analyzer.allTransactions();
        assertEquals(3, transactions.size(), "Should be saved all transactions");
    }

    @Test
    public void testAllTransactionsByUserWithNullID() {
        assertThrows(IllegalArgumentException.class, () -> analyzer.allTransactionsByUser(null), "Should throw exception with null ID");
    }

    @Test
    public void testAllAccountIDs() {
        List<String> accountIDs = analyzer.allAccountIDs();
        assertEquals(2, accountIDs.size(), "Should return correct number of account IDs");
        assertTrue(accountIDs.contains("AC00128"), "Should contain account ID 'AC00128'");
        assertTrue(accountIDs.contains("AC00455"), "Should contain account ID 'AC00455'");
    }

    @Test
    public void testTransactionCountByChannel() {
        Map<Channel, Integer> countByChannel = analyzer.transactionCountByChannel();
        assertEquals(2, countByChannel.get(Channel.ATM), "Should return correct count for ATM channel");
        assertEquals(1, countByChannel.get(Channel.ONLINE), "Should return correct count for ONLINE channel");
    }

    @Test
    public void testAmountSpentByUser() {
        double amountSpent = analyzer.amountSpentByUser("AC00128");
        assertEquals(40, amountSpent, "Should return correct amount spent by user 'AC00128'");
    }

    @Test
    public void testAllTransactionsByUser() {
        List<Transaction> transactions = analyzer.allTransactionsByUser("AC00128");
        assertEquals(2, transactions.size(), "Should return all transactions for user 'AC00128'");
    }

    @Test
    public void accountRatingWithInvalidId() {
        assertEquals(0, analyzer.accountRating("INVALID"), "Should return 0 for invalid account ID");
    }


    @Test
    public void testAccountRating() {
        assertEquals(0.0, analyzer.accountRating("AC00455"),
            "AC00455 rating should be 0.0 because no rule is applicable");

        assertEquals(0.05, analyzer.accountRating("AC00128"),
            "AC00128 rating should be 0.05 because only SmallTransactionsRule is applicable");
    }

    @Test
    public void testAccountsRisk() {
        SortedMap<String, Double> result = new TreeMap<>();
        result.put("AC00128", 0.05);
        result.put("AC00455", 0.0);

        assertEquals(result, analyzer.accountsRisk(),
            "Should return correct risk for each account");
    }
    
}

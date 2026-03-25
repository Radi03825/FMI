package bg.sofia.uni.fmi.mjt.frauddetector.rule;

import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Channel;
import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationsRuleTest {
    private LocationsRule rule;

    @BeforeEach
    public void setUp() {
        rule = new LocationsRule(3, 1.0);
    }

    @Test
    public void testApplicableWithNullTransactions() {
        assertFalse(rule.applicable(null), "Rule should not be applicable for null transactions list");
    }

    @Test
    public void testApplicableWithEmptyTransactions() {
        assertFalse(rule.applicable(List.of()), "Rule should not be applicable for empty transactions list");
    }

    @Test
    public void testApplicableWithTransactionsLessThanThreshold() {
        List<Transaction> transactions = List.of(
            new Transaction("1", "1", 100.0,
                LocalDateTime.of(2023, 1, 1, 0, 0), "Location1", Channel.ONLINE)
        );
        assertFalse(rule.applicable(transactions), "Rule should not be applicable for transactions list smaller than threshold");
    }

    @Test
    public void testApplicableWithTransactionsMeetingThreshold() {
        List<Transaction> transactions = List.of(
            new Transaction("1", "1", 100.0,
                LocalDateTime.of(2023, 1, 1, 0, 0), "Location1", Channel.ONLINE),
            new Transaction("2", "2", 200.0,
                LocalDateTime.of(2023, 1, 1, 1, 0), "Location2", Channel.BRANCH),
            new Transaction("3", "3", 300.0,
                LocalDateTime.of(2023, 1, 1, 2, 0), "Location3", Channel.ATM)
        );
        assertTrue(rule.applicable(transactions), "Rule should be applicable for transactions list meeting the threshold with different locations");
    }

    @Test
    public void testApplicableWithTransactionsNotMeetingThreshold() {
        List<Transaction> transactions = List.of(
            new Transaction("1", "1", 100.0,
                LocalDateTime.of(2023, 1, 1, 0, 0), "Location1", Channel.ONLINE),
            new Transaction("2", "2", 200.0,
                LocalDateTime.of(2023, 1, 1, 1, 0), "Location1", Channel.BRANCH),
            new Transaction("3", "3", 300.0,
                LocalDateTime.of(2023, 1, 1, 2, 0), "Location2", Channel.ONLINE)
        );
        assertFalse(rule.applicable(transactions), "Rule should not be applicable for transactions list not meeting the threshold with different locations");
    }
    
}

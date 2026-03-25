package bg.sofia.uni.fmi.mjt.frauddetector.rule;

import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Channel;
import bg.sofia.uni.fmi.mjt.frauddetector.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrequencyRuleTest {
    private FrequencyRule frequencyRule;
    private static final double WEIGHT = 1.0;

    @BeforeEach
    public void setUp() {
        frequencyRule = new FrequencyRule(3, Duration.ofHours(1), WEIGHT);
    }

    @Test
    public void testCreateRuleWithInvalidWeight() {
        assertThrows(IllegalArgumentException.class, () -> new FrequencyRule(3, Duration.ofHours(1), 7.0), "Weight should be in the range [0.0, 1.0]");
    }

    @Test
    public void testApplicableWithNullTransactions() {
        assertFalse(frequencyRule.applicable(null));
    }

    @Test
    public void testApplicableWithEmptyTransactions() {
        assertFalse(frequencyRule.applicable(List.of()));
    }

    @Test
    public void testApplicableWithTransactionsLessThanThreshold() {
        List<Transaction> transactions = List.of(
            new Transaction("TX000001", "AC00128", 100,
                LocalDateTime.of(2023, 4, 11, 16, 30), "San Diego", Channel.ATM)
        );
        assertFalse(frequencyRule.applicable(transactions));
    }

    @Test
    public void testApplicableWithTransactionsMeetingThreshold() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 1, 0, 0);
        List<Transaction> transactions = List.of(
            new Transaction("1", "1",  100, dateTime, "Location1", Channel.BRANCH),
            new Transaction("2", "2", 100, dateTime.plusMinutes(20), "Location2", Channel.ONLINE),
            new Transaction("3", "3",  100, dateTime.plusMinutes(30), "Location3", Channel.ATM)
        );

        assertTrue(frequencyRule.applicable(transactions));
    }

    @Test
    public void testApplicableWithTransactionsNotMeetingThreshold() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 4, 7, 5, 33);
        List<Transaction> transactions = List.of(
            new Transaction("1", "1",  100, dateTime, "Location1", Channel.ONLINE),
            new Transaction("2", "2",  100, dateTime.plusMinutes(20), "Location2", Channel.ATM),
            new Transaction("3", "3",  100, dateTime.plusHours(2), "Location3", Channel.BRANCH)
        );

        assertFalse(frequencyRule.applicable(transactions));
    }
    
}

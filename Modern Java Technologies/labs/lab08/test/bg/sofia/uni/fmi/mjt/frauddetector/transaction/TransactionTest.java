package bg.sofia.uni.fmi.mjt.frauddetector.transaction;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTest {
    @Test
    public void testOfWithValidTransactionString() {
        String transactionString = "TX000001,AC00128,100.0,2023-01-01 00:00:00,San Diego,ATM";
        Transaction transaction = Transaction.of(transactionString);

        assertEquals("TX000001", transaction.transactionID(), "Transaction ID should match");
        assertEquals("AC00128", transaction.accountID(), "Account ID should match");
        assertEquals(100.0, transaction.transactionAmount(), "Transaction amount should match");
        assertEquals(LocalDateTime.of(2023, 1, 1, 0, 0), transaction.transactionDate(), "Transaction date should match");
        assertEquals("San Diego", transaction.location(), "Location should match");
        assertEquals(Channel.ATM, transaction.channel(), "Channel should match");
    }

    @Test
    public void testOfWithInvalidTransactionString() {
        String invalidTransactionString = "TX000003,AC00019,126.29,2023-07-10 18:16:08,Mesa";

        assertThrows(IllegalArgumentException.class, () -> Transaction.of(invalidTransactionString),
            "Should throw ArrayIndexOutOfBoundsException for invalid transaction string");
    }

    @Test
    public void testOfWithInvalidDateFormat() {
        String invalidDateFormatString = "TX000004,AC00070,100.0,2023/01/01 00:00:00,Oklahoma City,ONLINE";

        assertThrows(Exception.class, () -> Transaction.of(invalidDateFormatString),
            "Should throw DateTimeParseException for invalid date format");
    }

    @Test
    public void testOfWithInvalidChannel() {
        String invalidChannelString = "TX000012,AC00459,190.02,2023-02-06 17:30:00,Memphis,INVALID";

        assertThrows(IllegalArgumentException.class, () -> Transaction.of(invalidChannelString),
            "Should throw IllegalArgumentException for invalid channel");
    }
    
}

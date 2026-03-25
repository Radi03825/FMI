package bg.sofia.uni.fmi.mjt.frauddetector.transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Transaction(String transactionID, String accountID, double transactionAmount,
                          LocalDateTime transactionDate, String location, Channel channel) {

    private static final String TRANSACTION_ATTRIBUTE_DELIMITER = ",";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    // indexes of the attributes in the transaction string
    private static final int TRANSACTION_ID_INDEX = 0;
    private static final int ACCOUNT_ID_INDEX = 1;
    private static final int TRANSACTION_AMOUNT_INDEX = 2;
    private static final int TRANSACTION_DATE_INDEX = 3;
    private static final int LOCATION_INDEX = 4;
    private static final int CHANNEL_INDEX = 5;

    private static final int TRANSACTION_ATTRIBUTES_COUNT = 6;

    public static Transaction of(String line) {
        String[] tokens = line.split(TRANSACTION_ATTRIBUTE_DELIMITER);

        if (tokens.length != TRANSACTION_ATTRIBUTES_COUNT) {
            throw new IllegalArgumentException("Invalid transaction format");
        }

        String transactionID = tokens[TRANSACTION_ID_INDEX];
        String accountID = tokens[ACCOUNT_ID_INDEX];
        double transactionAmount = Double.parseDouble(tokens[TRANSACTION_AMOUNT_INDEX]);

        String dateAsString = tokens[TRANSACTION_DATE_INDEX];
        LocalDateTime transactionDate = LocalDateTime.parse(dateAsString, DATE_TIME_FORMATTER);
        String location = tokens[LOCATION_INDEX];
        Channel channel = Channel.valueOf(tokens[CHANNEL_INDEX].toUpperCase());

        return new Transaction(transactionID, accountID, transactionAmount, transactionDate, location, channel);
    }
    
}

package bg.sofia.uni.fmi.mjt.newsfeed.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        out.value(value.format(FORMATTER));
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        try {
            String value = in.nextString();
            if (value.endsWith("Z")) {
                value = value.substring(0, value.length() - 1);
            } else if (value.contains("+00:00")) {
                value = value.substring(0, value.indexOf("+00:00"));
            }
            return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            throw new IOException("Failed to parse LocalDateTime", e);
        }
    }
    
}

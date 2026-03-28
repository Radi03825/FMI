package bg.sofia.uni.fmi.mjt.newsfeed.adapter;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalDateTimeAdapterTest {

    private LocalDateTimeAdapter adapter = new LocalDateTimeAdapter();

    @Test
    public void testWrite() throws IOException {
        LocalDateTime dateTime = LocalDateTime.of(2025, Month.JANUARY, 17, 15, 19, 38);

        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);

        adapter.write(jsonWriter, dateTime);
        jsonWriter.close();

        String expected = "\"2025-01-17T15:19:38\"";
        String result = stringWriter.toString();
        assertEquals(expected, result, "Date is written correctly");
    }

    @Test
    public void testReadWithTrailingZ() throws IOException {
        String input = "\"2023-03-15T10:15:33Z\"";
        JsonReader jsonReader = new JsonReader(new StringReader(input));

        LocalDateTime result = adapter.read(jsonReader);
        LocalDateTime expected = LocalDateTime.of(2023, 3, 15, 10, 15, 33);
        assertEquals(expected, result, "Date which ends on Z, is read correctly");
    }

    @Test
    public void testReadWithPlusOffset() throws IOException {
        String input = "\"2024-04-25T10:15:30+00:00\"";
        JsonReader jsonReader = new JsonReader(new StringReader(input));

        LocalDateTime result = adapter.read(jsonReader);
        LocalDateTime expected = LocalDateTime.of(2024, 4, 25, 10, 15, 30);
        assertEquals(expected, result, "Date with +00:00 is read correctly");
    }

    @Test
    public void testReadValidIsoString() throws IOException {
        String input = "\"2023-03-25T10:15:30\"";
        JsonReader jsonReader = new JsonReader(new StringReader(input));

        LocalDateTime result = adapter.read(jsonReader);
        LocalDateTime expected = LocalDateTime.of(2023, 3, 25, 10, 15, 30);
        assertEquals(expected, result, "Date is read correctly");
    }

    @Test
    public void testReadInvalidDateThrowsIOException() {
        String input = "\"invalid-date\"";
        JsonReader jsonReader = new JsonReader(new StringReader(input));

        assertThrows(IOException.class, () -> adapter.read(jsonReader), "Invalid date throws IOException");
    }

}

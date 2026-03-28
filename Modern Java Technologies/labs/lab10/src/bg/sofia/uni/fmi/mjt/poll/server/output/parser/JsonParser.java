package bg.sofia.uni.fmi.mjt.poll.server.output.parser;

public class JsonParser implements OutputParser {

    @Override
    public String createResponse(String status, String message) {
        if (status == null || message == null) {
            throw new IllegalArgumentException("Status and message cannot be null");
        }

        return String.format("{\"status\":\"%s\",\"message\":\"%s\"}", status, message);
    }

}

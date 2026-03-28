package bg.sofia.uni.fmi.mjt.newsfeed.exceptions;

public class BadRequestException extends ApiException {

    public BadRequestException(Integer statusCode, String code, String message) {
        super(statusCode, code, message);
    }

    public BadRequestException(Integer statusCode, String code, String message, Throwable cause) {
        super(statusCode, code, message, cause);
    }

    @Override
    public String toString() {
        return String.format("BadRequestException: { %s }", super.toString());
    }
    
}

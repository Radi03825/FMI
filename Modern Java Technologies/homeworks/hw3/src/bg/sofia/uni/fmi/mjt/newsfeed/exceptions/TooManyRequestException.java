package bg.sofia.uni.fmi.mjt.newsfeed.exceptions;

public class TooManyRequestException extends ApiException {

    public TooManyRequestException(Integer statusCode, String code, String message) {
        super(statusCode, code, message);
    }

    public TooManyRequestException(Integer statusCode, String code, String message, Throwable cause) {
        super(statusCode, code, message, cause);
    }

    @Override
    public String toString() {
        return String.format("TooManyRequestException: { %s }", super.toString());
    }
    
}

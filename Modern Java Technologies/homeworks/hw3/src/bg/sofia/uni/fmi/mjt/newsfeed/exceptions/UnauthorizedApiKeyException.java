package bg.sofia.uni.fmi.mjt.newsfeed.exceptions;

public class UnauthorizedApiKeyException extends ApiException {

    public UnauthorizedApiKeyException(Integer statusCode, String code, String message) {
        super(statusCode, code, message);
    }

    public UnauthorizedApiKeyException(Integer statusCode, String code, String message, Throwable cause) {
        super(statusCode, code, message, cause);
    }

    @Override
    public String toString() {
        return String.format("UnauthorizedApiKeyException: { %s }", super.toString());
    }

}

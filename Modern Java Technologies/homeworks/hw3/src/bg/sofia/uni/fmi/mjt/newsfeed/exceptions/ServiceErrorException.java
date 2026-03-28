package bg.sofia.uni.fmi.mjt.newsfeed.exceptions;

public class ServiceErrorException extends ApiException {

    public ServiceErrorException(Integer statusCode, String code, String message) {
        super(statusCode, code, message);
    }

    public ServiceErrorException(Integer statusCode, String code, String message, Throwable cause) {
        super(statusCode, code, message, cause);
    }

    @Override
    public String toString() {
        return String.format("ServiceErrorException: { %s }", super.toString());
    }
    
}

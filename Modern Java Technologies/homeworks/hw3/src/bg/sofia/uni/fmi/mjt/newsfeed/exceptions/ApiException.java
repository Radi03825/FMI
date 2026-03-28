package bg.sofia.uni.fmi.mjt.newsfeed.exceptions;

public class ApiException extends Exception {

    private Integer statusCode;
    private String code;

    public ApiException(Integer statusCode, String code, String message) {
        super(message);
        this.statusCode = statusCode;
        this.code = code;
    }

    public ApiException(Integer statusCode, String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("ApiException: %s, statusCode: %d, code: %s", super.getMessage(), statusCode, code);
    }

}

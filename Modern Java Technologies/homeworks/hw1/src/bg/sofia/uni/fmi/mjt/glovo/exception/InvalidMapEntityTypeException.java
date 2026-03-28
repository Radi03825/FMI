package bg.sofia.uni.fmi.mjt.glovo.exception;

public class InvalidMapEntityTypeException extends RuntimeException {

    public InvalidMapEntityTypeException(String message) {
        super(message);
    }

    public InvalidMapEntityTypeException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

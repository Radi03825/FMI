package bg.sofia.uni.fmi.mjt.poll.server.exceptions;

public class InvalidArgumentsException extends Exception {

    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

package bg.sofia.uni.fmi.mjt.poll.server.exceptions;

public class InvalidCommandTypeException extends Exception {

    public InvalidCommandTypeException(String message) {
        super(message);
    }

    public InvalidCommandTypeException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

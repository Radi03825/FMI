package bg.sofia.uni.fmi.mjt.poll.server.exceptions;

public class NoActivePollsException extends Exception {

    public NoActivePollsException(String message) {
        super(message);
    }

    public NoActivePollsException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

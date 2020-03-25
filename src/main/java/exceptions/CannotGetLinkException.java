package exceptions;

public class CannotGetLinkException extends RuntimeException{

    public CannotGetLinkException(String message, Throwable cause) {
        super(message, cause);
    }
}

package fullstack.reservation.exception;

public class MissMatchedPasswordException extends RuntimeException {

    public MissMatchedPasswordException() {
    }

    public MissMatchedPasswordException(String message) {
        super(message);
    }

    public MissMatchedPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissMatchedPasswordException(Throwable cause) {
        super(cause);
    }

    public MissMatchedPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

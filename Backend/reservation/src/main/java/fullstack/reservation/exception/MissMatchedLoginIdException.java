package fullstack.reservation.exception;

public class MissMatchedLoginIdException extends RuntimeException {
    public MissMatchedLoginIdException() {
    }

    public MissMatchedLoginIdException(String message) {
        super(message);
    }

    public MissMatchedLoginIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissMatchedLoginIdException(Throwable cause) {
        super(cause);
    }

    public MissMatchedLoginIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

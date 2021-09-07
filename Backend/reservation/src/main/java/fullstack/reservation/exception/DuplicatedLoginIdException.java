package fullstack.reservation.exception;

public class DuplicatedLoginIdException extends RuntimeException {
    public DuplicatedLoginIdException() {
    }

    public DuplicatedLoginIdException(String message) {
        super(message);
    }

    public DuplicatedLoginIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedLoginIdException(Throwable cause) {
        super(cause);
    }

    public DuplicatedLoginIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

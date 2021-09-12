package fullstack.reservation.exception;

public class DuplicatedSeatException extends RuntimeException {
    public DuplicatedSeatException() {
    }

    public DuplicatedSeatException(String message) {
        super(message);
    }

    public DuplicatedSeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedSeatException(Throwable cause) {
        super(cause);
    }

    public DuplicatedSeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

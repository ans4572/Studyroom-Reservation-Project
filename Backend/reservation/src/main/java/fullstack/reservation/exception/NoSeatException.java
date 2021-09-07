package fullstack.reservation.exception;

public class NoSeatException extends RuntimeException {
    public NoSeatException() {
    }

    public NoSeatException(String message) {
        super(message);
    }

    public NoSeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSeatException(Throwable cause) {
        super(cause);
    }

    public NoSeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package fullstack.reservation.exception;

public class NoReservationException extends RuntimeException {
    public NoReservationException() {
    }

    public NoReservationException(String message) {
        super(message);
    }

    public NoReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoReservationException(Throwable cause) {
        super(cause);
    }

    public NoReservationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

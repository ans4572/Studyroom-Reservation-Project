package fullstack.reservation.exception;

public class CurrentReservationException extends RuntimeException {
    public CurrentReservationException() {
    }

    public CurrentReservationException(String message) {
        super(message);
    }

    public CurrentReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrentReservationException(Throwable cause) {
        super(cause);
    }

    public CurrentReservationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

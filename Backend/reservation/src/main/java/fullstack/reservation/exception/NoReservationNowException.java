package fullstack.reservation.exception;

public class NoReservationNowException extends RuntimeException {
    public NoReservationNowException() {
    }

    public NoReservationNowException(String message) {
        super(message);
    }

    public NoReservationNowException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoReservationNowException(Throwable cause) {
        super(cause);
    }

    public NoReservationNowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

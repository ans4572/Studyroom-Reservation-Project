package fullstack.reservation.exception;

public class NoAvailableTicketException extends RuntimeException {
    public NoAvailableTicketException() {
        super();
    }

    public NoAvailableTicketException(String message) {
        super(message);
    }

    public NoAvailableTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAvailableTicketException(Throwable cause) {
        super(cause);
    }

    protected NoAvailableTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

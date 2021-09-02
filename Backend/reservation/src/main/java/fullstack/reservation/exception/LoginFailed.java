package fullstack.reservation.exception;

public class LoginFailed extends RuntimeException {
    public LoginFailed() {
        super();
    }

    public LoginFailed(String message) {
        super(message);
    }

    public LoginFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailed(Throwable cause) {
        super(cause);
    }

    protected LoginFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

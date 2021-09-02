package fullstack.reservation.exception;

public class InterceptorException extends RuntimeException {
    public InterceptorException(String s) {
    }

    public InterceptorException() {
        super();
    }

    public InterceptorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterceptorException(Throwable cause) {
        super(cause);
    }

    protected InterceptorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

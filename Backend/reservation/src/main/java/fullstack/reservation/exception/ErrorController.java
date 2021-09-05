package fullstack.reservation.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity loginFailedExceptionHandler(LoginFailedException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity interceptorExceptionHandler(InterceptorException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), "로그인 후 이용 가능합니다.", request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorResult errorResult = new ErrorResult(LocalDateTime.now(), "validation failed", ex.getBindingResult().toString());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }
}

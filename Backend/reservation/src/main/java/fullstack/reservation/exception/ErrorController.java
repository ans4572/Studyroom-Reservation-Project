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
    public ResponseEntity generalExceptionHandler(IllegalStateException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    //이용권이 만료됬을 때
    @ExceptionHandler
    public ResponseEntity noAvailableTicketException(NoAvailableTicketException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    
    //이용중이지 않은 사용자가 퇴실을 눌렀을 때
    @ExceptionHandler
    public ResponseEntity noReservationNowExceptionHandler(NoReservationNowException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    
    //중복 아이디 가입
    @ExceptionHandler
    public ResponseEntity duplicatedLoginIdExceptionHandler(DuplicatedLoginIdException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    
    //아이디가 틀렸을 때
    @ExceptionHandler
    public ResponseEntity missMatchedLoginIdExceptionHandler(MissMatchedLoginIdException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    //비밀번호 입력이 틀렸을 때
    @ExceptionHandler
    public ResponseEntity MissMatchedPasswordExceptionHandler(MissMatchedPasswordException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    
    //중복 예약할경우
    @ExceptionHandler
    public ResponseEntity noReservationExceptionHandler(CurrentReservationException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    //좌석 중복
    @ExceptionHandler(DuplicatedSeatException.class)
    public ResponseEntity noSeatExceptionHandler(Exception ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity loginFailedExceptionHandler(Exception ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    
    //인터셉터 에러
    @ExceptionHandler
    public ResponseEntity interceptorExceptionHandler(InterceptorException ex, WebRequest request) {
        ErrorResult errorResult =
                new ErrorResult(LocalDateTime.now(), "로그인 후 이용 가능합니다.", request.getDescription(false));

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    
    //검증 핸들러
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorResult errorResult = new ErrorResult(LocalDateTime.now(), "validation failed", ex.getBindingResult().toString());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }
}

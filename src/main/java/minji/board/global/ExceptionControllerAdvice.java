package minji.board.global;


import io.swagger.models.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    /*
        비즈니스로직 에러 예외처리
     */
    @ExceptionHandler(BoardException.class)
    protected ResponseEntity<ExceptionResponse> handleBusinessCustomException(BoardException ex, HttpServletRequest request)
    {
        ExceptionResponse response = ExceptionResponse.of(ex.getBoardExceptionCode(), request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /*
        Valid 검증 실패 예외처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request){
        ExceptionResponse response = ExceptionResponse.of(ex, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    /*
        지원하지 않는 HTTP method 호출할 경우 예외처리
    */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();
        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if(!CollectionUtils.isEmpty(supportedMethods)){
            headers.setAllow(supportedMethods);
        }
        ExceptionResponse response = ExceptionResponse.of(ex, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /*
        이외의 모든 에러
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception ex, HttpServletRequest request){
        ExceptionResponse response = ExceptionResponse.of(ex,request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

package minji.board.global;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {

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
}

package minji.board.global;


import org.springframework.http.ResponseEntity;
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

}

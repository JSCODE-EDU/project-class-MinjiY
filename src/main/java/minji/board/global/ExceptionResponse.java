package minji.board.global;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Getter
// Response 포맷 지정(JSON)
public class ExceptionResponse {

    private String message;
    private String code;
    private HttpStatus status;
    private String uri;
    private LocalDateTime timestamp;

    // Constructor
    public ExceptionResponse(BoardExceptionCode code, String uri){
        this.message = code.getMessage();
        this.code = code.getCode();
        this.status = code.getStatus();
        this.uri = uri;
        this.timestamp = LocalDateTime.now();
    }

    // of
    public static ExceptionResponse of(BoardExceptionCode code, HttpServletRequest request ){
        return new ExceptionResponse(code ,request.getRequestURI());
    }

}

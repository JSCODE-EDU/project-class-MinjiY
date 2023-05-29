package minji.board.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardExceptionCode {

    INVALID_INPUT_VALUE("Invalid Input Value", "E001", HttpStatus.BAD_REQUEST),
    BOARD_NOT_FOUND( "Board Not Found.","B001", HttpStatus.NOT_FOUND);

    private final String message;
    private final String code;
    private final HttpStatus status;

    public String getCode(){return this.code;}
    public String getMessage(){
        return this.message;
    }
}

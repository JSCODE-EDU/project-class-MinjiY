package minji.board.global;

import lombok.Getter;

@Getter
public class BoardException extends RuntimeException{

    private BoardExceptionCode boardExceptionCode;

    public BoardException(BoardExceptionCode code){
        this.boardExceptionCode = code;
    }

}

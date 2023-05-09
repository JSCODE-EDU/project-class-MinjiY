package minji.board.controller.dto;


import lombok.Getter;
import minji.board.model.BoardEntity;

@Getter
public class BoardRequestDTO {
    public String title;
    public String content;

    public BoardEntity toBoardEntity(){
        return new BoardEntity(this.title, this.content);
    }
}

package minji.board.controller.dto;


import minji.board.model.BoardEntity;

public class BoardRequestDTO {
    public String title;
    public String content;

    public BoardEntity toBoardEntity(){
        return new BoardEntity(this.title, this.content);
    }
}

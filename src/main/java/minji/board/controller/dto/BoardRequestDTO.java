package minji.board.controller.dto;


import lombok.Getter;
import minji.board.model.BoardEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class BoardRequestDTO {
    @NotBlank(message = "제목을 입력해주세요")
    public String title;
    public String content;

    public BoardEntity toBoardEntity(){
        return new BoardEntity(this.title, this.content);
    }
}

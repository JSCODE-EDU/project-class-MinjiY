package minji.board.controller.dto;


import lombok.Getter;
import minji.board.model.BoardEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class BoardRequestDTO {
    @NotBlank(message = "제목을 입력해주세요")
    @Size(message = "제목은 1글자 이상, 15글자 이하입니다.", min = 1, max = 15)
    public String title;

    @NotNull
    @Size(message = "내용은 1글자 이상 1000글자 이하여야 합니다.", min = 1, max = 1000)
    public String content;

    public BoardEntity toBoardEntity(){
        return new BoardEntity(this.title, this.content);
    }
}

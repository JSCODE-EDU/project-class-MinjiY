package minji.board.controller.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import minji.board.model.BoardEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Api
public class BoardRequestDTO {
    @ApiModelProperty(
            value= "게시글의 제목",
            name = "title",
            dataType = "String",
            example = "1번 게시글의 제목",
            notes="제목은 1글자 이상, 15글자 이하입니다."
    )
    @NotBlank(message = "제목을 입력해주세요")
    @Size(message = "제목은 1글자 이상, 15글자 이하입니다.", min = 1, max = 15)
    public String title;

    @ApiModelProperty(
            value= "게시글의 내용",
            name = "content",
            dataType = "String",
            example = "1번 게시글의 내용",
            notes="제목은 1글자 이상, 1000글자 이하입니다."
    )
    @NotNull
    @Size(message = "내용은 1글자 이상 1000글자 이하여야 합니다.", min = 1, max = 1000)
    public String content;

    public BoardEntity toBoardEntity(){
        return new BoardEntity(this.title, this.content);
    }
}

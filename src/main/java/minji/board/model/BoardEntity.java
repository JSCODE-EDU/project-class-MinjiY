package minji.board.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "board")
@NoArgsConstructor
@Getter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;
    private String content;

    public BoardEntity(String title, String content){
        this.title = title;
        this.content = content;
    }
    public void setBoard(String title, String content){
        this.title = title;
        this.content = content;
    }
}

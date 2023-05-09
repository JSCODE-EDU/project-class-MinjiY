package minji.board.controller;

import lombok.RequiredArgsConstructor;
import minji.board.model.BoardEntity;
import minji.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<List<BoardEntity>> findBoard(){
        List<BoardEntity> boardEntityList = boardService.getBoardList();
        return new ResponseEntity<>(boardEntityList, HttpStatus.OK);
    }
}

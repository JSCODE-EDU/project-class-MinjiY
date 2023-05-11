package minji.board.controller;

import lombok.RequiredArgsConstructor;
import minji.board.controller.dto.BoardRequestDTO;
import minji.board.model.BoardEntity;
import minji.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<List<BoardEntity>> findBoardList(){
        List<BoardEntity> response = boardService.getBoardList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity<BoardEntity> createBoard(
            @RequestBody BoardRequestDTO boardRequestDTO
    ){
        BoardEntity response = boardService.create(boardRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<BoardEntity> findBoard(
            @PathVariable Long boardId
    ){
        BoardEntity response = boardService.getBoard(boardId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<BoardEntity> UpdateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardRequestDTO boardRequestDTO
    ){
        BoardEntity response = boardService.setBoard(boardId, boardRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity deleteBoard(
            @PathVariable Long boardId
    ){
        String response = boardService.deleteBoard(boardId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/boards/search")
    public ResponseEntity<List<BoardEntity>> searchBoard(
        @RequestParam String keyword
    ){
        List<BoardEntity> response = boardService.searchList(keyword);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

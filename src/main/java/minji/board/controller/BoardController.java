package minji.board.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import minji.board.controller.dto.BoardRequestDTO;
import minji.board.global.ExceptionResponse;
import minji.board.model.BoardEntity;
import minji.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value ="게시글 리스트 조회", notes="가장 최근 작성된 게시글을 기준으로 최대 100개 조회")
    @ApiResponse(code = 200, message = "조회 성공", response = BoardEntity.class, responseContainer = "List")
    @GetMapping("/boards")
    public ResponseEntity<List<BoardEntity>> findBoardList(){
        List<BoardEntity> response = boardService.getBoardList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value= "게시글 작성", notes="게시글 작성")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "생성 성공", response = BoardEntity.class),
            @ApiResponse(code = 400, message = "생성 실패", response = ExceptionResponse.class)
        }
    )
    @PostMapping("/boards")
    public ResponseEntity<BoardEntity> createBoard(
            @Valid @RequestBody BoardRequestDTO boardRequestDTO
    ){
        BoardEntity response = boardService.create(boardRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value ="게시글 조회", notes="특정 게시글 조회")
    @ApiResponse(code = 200, message = "조회 성공", response = BoardEntity.class)
    @GetMapping("/boards/{boardId}")
    public ResponseEntity<BoardEntity> findBoard(
            @ApiParam(value = "boardId", required = true, example = "1")
            @PathVariable Long boardId
    ){
        BoardEntity response = boardService.getBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value= "게시글 수정", notes="게시글 수정")
    @ApiResponses(value= {
            @ApiResponse(code = 201, message = "수정 성공", response = BoardEntity.class),
            @ApiResponse(code = 400, message = "수정 실패", response = ExceptionResponse.class)
    }
    )
    @PutMapping("/boards/{boardId}")
    public ResponseEntity<BoardEntity> UpdateBoard(
            @ApiParam(value = "boardId", required = true, example = "1")
            @PathVariable Long boardId,
            @Valid @RequestBody BoardRequestDTO boardRequestDTO
    ){
        BoardEntity response = boardService.setBoard(boardId, boardRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @ApiOperation(value ="게시글 삭제", notes="특정 게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "삭제 성공", response = String.class),
            @ApiResponse(code = 400, message = "삭제 실패", response = String.class)
    })
    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity deleteBoard(
            @ApiParam(value = "boardId", required = true, example = "1")
            @PathVariable Long boardId
    ){
        String response = boardService.deleteBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value ="게시글 검색", notes="특정 검색어가 들어가있는게 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공", response = BoardEntity.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "조회 실패, 못찾음", response = BoardEntity.class, responseContainer = "List")
    })@GetMapping("/boards/search")
    public ResponseEntity<List<BoardEntity>> searchBoard(
            @ApiParam(value = "keyword", required = true, example = "word")
            @Valid @RequestParam @NotBlank @Size(message = "검색어는 1글자 이상입니다.", min = 1) String keyword
    ){
        List<BoardEntity> response = boardService.searchList(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

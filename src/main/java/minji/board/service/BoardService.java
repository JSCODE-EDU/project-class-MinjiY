package minji.board.service;

import lombok.RequiredArgsConstructor;
import minji.board.controller.dto.BoardRequestDTO;
import minji.board.global.BoardException;
import minji.board.global.BoardExceptionCode;
import minji.board.model.BoardEntity;
import minji.board.repository.BoardRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardEntity> getBoardList(){
        List<BoardEntity> findBoardList = boardRepository.findTop100ByOrderByCreatedAtDesc();
        return findBoardList;
    }

    @Transactional(readOnly = true)
    public BoardEntity getBoard(Long boardId){
        BoardEntity board = boardRepository.findById(boardId).orElseThrow(()-> new BoardException(BoardExceptionCode.BOARD_NOT_FOUND));
        return board;
    }

    @Transactional
    public BoardEntity create(BoardRequestDTO boardRequestDTO){
        BoardEntity board = boardRepository.save(boardRequestDTO.toBoardEntity());
        return board;
    }

    @Transactional
    public BoardEntity setBoard(Long boardId, BoardRequestDTO boardRequestDTO){
        BoardEntity board = boardRepository.findById(boardId).orElseThrow(()-> new BoardException(BoardExceptionCode.BOARD_NOT_FOUND));
        board.setBoard(boardRequestDTO.getTitle(), boardRequestDTO.getContent());
        BoardEntity responseBoard = boardRepository.save(board);
        return responseBoard;
    }
    @Transactional
    public String deleteBoard(Long boardId){
        BoardEntity board = boardRepository.findById(boardId).orElseThrow(()-> new BoardException(BoardExceptionCode.BOARD_NOT_FOUND));
        if(!board.getBoardId().equals(boardId)){
            //삭제를 요청하는 게시글을 찾아서 없으면 에러 response 처리 후 하는 분기처리, 큰 의미는 없음
            boardRepository.deleteById(boardId);
        }
        return "DeleteBoard OK";
    }

    public List<BoardEntity> searchList(String keyword){
        List<BoardEntity> findBoardList = boardRepository.findTop100ByTitleContainingOrderByCreatedAtDesc(keyword);
        return findBoardList;
    }
}

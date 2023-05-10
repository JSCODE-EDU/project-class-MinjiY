package minji.board.service;

import lombok.RequiredArgsConstructor;
import minji.board.controller.dto.BoardRequestDTO;
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
        List<BoardEntity> boardList = boardRepository.findTop100ByOrderByCreatedAtDesc();
        return boardList;
    }

    @Transactional(readOnly = true)
    public BoardEntity getBoard(Long boardId){
        BoardEntity board = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        return board;
    }

    @Transactional
    public BoardEntity create(BoardRequestDTO boardRequestDTO){
        BoardEntity board = boardRepository.save(boardRequestDTO.toBoardEntity());
        return board;
    }

    @Transactional
    public BoardEntity setBoard(Long boardId, BoardRequestDTO boardRequestDTO){
        BoardEntity findBoard = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        findBoard.setBoard(boardRequestDTO.getTitle(), boardRequestDTO.getContent());
        BoardEntity board = boardRepository.save(findBoard);
        return board;
    }
    @Transactional
    public String deleteBoard(Long boardId){
        boardRepository.deleteById(boardId);
        return "DeleteBoard OK";
    }

    @Transactional(readOnly = true)
    public List<BoardEntity> searchList(String keyword){
        List<BoardEntity> boardList = boardRepository.findTop100ByTitleContainingOrderByCreatedAtDesc(keyword);
        return boardList;
    }
}

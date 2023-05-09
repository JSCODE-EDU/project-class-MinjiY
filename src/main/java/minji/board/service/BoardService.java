package minji.board.service;

import lombok.RequiredArgsConstructor;
import minji.board.model.BoardEntity;
import minji.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardEntity> getBoardList(){
        List<BoardEntity> findBoardList = boardRepository.findAll();
        return findBoardList;
    }
}

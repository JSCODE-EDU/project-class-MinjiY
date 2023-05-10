package minji.board.repository;

import minji.board.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}

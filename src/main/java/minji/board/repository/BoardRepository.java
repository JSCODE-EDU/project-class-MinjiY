package minji.board.repository;

import minji.board.model.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findTop100ByOrderByCreatedAtDesc();
    List<BoardEntity> findTop100ByTitleContainingOrderByCreatedAtDesc(String keyword);
}

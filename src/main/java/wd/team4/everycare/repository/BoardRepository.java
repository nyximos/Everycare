package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Board;
import wd.team4.everycare.domain.BoardCategory;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByCategory(BoardCategory category);

    @EntityGraph(attributePaths = {"product"})
    List<Board> findByCategoryAndProductId(BoardCategory category, Long id);

    @EntityGraph(attributePaths = {"product"})
    List<Board> findByProductId(Long productId);
}

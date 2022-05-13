package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}

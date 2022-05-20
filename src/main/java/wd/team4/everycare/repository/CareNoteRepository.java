package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.CareSitter;

import java.time.LocalDate;
import java.util.List;

public interface CareNoteRepository extends JpaRepository<CareNote, Long> {
    List<CareNote> findAllByCareSitterAndDate(CareSitter careSitter, LocalDate today);
}

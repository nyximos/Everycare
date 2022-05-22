package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.CareSitter;

import java.time.LocalDate;
import java.util.List;

public interface CareNoteRepository extends JpaRepository<CareNote, Long> {

    @Query("select c from CareNote c where c.careSitter = :careSitter and c.date = :date")
    List<CareNote> findAllByCareSitterAndDate(@Param("careSitter") CareSitter careSitter, @Param("date") LocalDate date);

//    List<CareNote> findAllByCareSitterAndDate(CareSitter careSitter, LocalDate date);
}

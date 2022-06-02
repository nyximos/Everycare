package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.Member;

import java.time.LocalDate;
import java.util.List;

public interface CareNoteRepository extends JpaRepository<CareNote, Long> {

    @Query("select c from CareNote c where c.careSitter = :careSitter and c.date = :date and c.endTime is null")
    List<CareNote> findAllByCareSitterAndDate(@Param("careSitter") CareSitter careSitter, @Param("date") LocalDate date);

    @Query("select c from CareNote c where c.member = :member and c.date = :date")
    List<CareNote> findAllByMemberAndDate(@Param("member") Member member,  @Param("date") LocalDate date);

}

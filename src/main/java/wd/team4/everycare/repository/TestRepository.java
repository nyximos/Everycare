package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wd.team4.everycare.domain.Dates;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Dates, Long> {

    @Query("select d from Dates d where d.date = :day")
    Optional<Dates> findByDate2(@Param("day") LocalDate day);
}
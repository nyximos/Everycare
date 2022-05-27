package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByStatus(int i);

}

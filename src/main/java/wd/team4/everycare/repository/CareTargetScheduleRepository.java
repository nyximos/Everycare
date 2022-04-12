package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.dto.CareTargetScheduleDTO;

import java.util.List;
import java.util.Optional;

public interface CareTargetScheduleRepository extends JpaRepository<CareTargetSchedule, Integer> {

    List<CareTargetSchedule> findByCareTarget_Id(Long id);
//    @Query("select cs from CareTargetSchedule cs where cs.careTarget = :careTargets")
//    List<CareTargetSchedule> findByCareTarget(List<CareTarget> careTargets);
}

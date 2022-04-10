package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.dto.CareTargetScheduleDTO;

import java.util.List;
import java.util.Optional;

public interface CareTargetScheduleRepository extends JpaRepository<CareTargetSchedule, Integer> {

    List<CareTargetSchedule> findByCareTarget_Id(Long id);
}

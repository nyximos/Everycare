package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.dto.CareTargetScheduleDTO;

public interface CareTargetScheduleRepository extends JpaRepository<CareTargetSchedule, Integer> {

    CareTargetScheduleDTO findByCareTarget_Id(Long id);
}

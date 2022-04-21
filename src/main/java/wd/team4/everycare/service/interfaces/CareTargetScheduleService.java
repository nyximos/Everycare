package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.dto.CareTargetScheduleDTO;

import java.util.List;

public interface CareTargetScheduleService {

    List<CareTargetScheduleDTO> findAllByCareTarget(Long id);

}

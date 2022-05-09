package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.util.List;

public interface CareTargetScheduleService {

    List<CareTargetScheduleDTO> findAllByCareTarget(Long id);

    ResponseEntity<MyResponse> save(CareTargetScheduleDTO careTargetScheduleDTO);

    CareTargetScheduleDTO webFindById(Long scheduleId);

    ResponseEntity<MyResponse> delete(Long scheduleId);

    ResponseEntity<MyResponse> update(CareTargetScheduleDTO careTargetScheduleDTO);
}
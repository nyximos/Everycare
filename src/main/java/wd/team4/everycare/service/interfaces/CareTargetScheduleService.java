package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.util.List;

public interface CareTargetScheduleService {

    ResponseEntity<MyResponse> getAll(Long id);

    ResponseEntity<MyResponse> getSchedule(Long scheduleId);

    ResponseEntity<MyResponse> save(CareTargetScheduleDTO careTargetScheduleDTO);

    ResponseEntity<MyResponse> delete(Long scheduleId);

    ResponseEntity<MyResponse> update(CareTargetScheduleDTO careTargetScheduleDTO);

    CareTargetScheduleDTO webFindById(Long scheduleId);

    List<CareTargetScheduleDTO> findAllByCareTarget(Long id);

}
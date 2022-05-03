package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.CareTargetScheduleDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.util.List;

public interface CareTargetScheduleService {

    List<CareTargetScheduleDTO> findAllByCareTarget(Long id);

    ResponseEntity<MyResponse> save(CareTargetScheduleDTO careTargetScheduleDTO);
}
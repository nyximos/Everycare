package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.careRecord.HealthRecordFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

public interface HealthRecordService {

    ResponseEntity<MyResponse> save(Long id, HealthRecordFormDTO healthRecordFormDTO);

    ResponseEntity<MyResponse> update(Long id, HealthRecordFormDTO healthRecordFormDTO);

    ResponseEntity<MyResponse> remove(Long id);
}

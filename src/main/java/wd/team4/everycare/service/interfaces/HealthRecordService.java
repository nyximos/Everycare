package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.response.MyResponse;

public interface HealthRecordService {

    ResponseEntity<MyResponse> getAll(Long id);

    ResponseEntity<MyResponse> get(Long id);

    ResponseEntity<MyResponse> save(Long id, HealthRecordFormDTO healthRecordFormDTO);

    ResponseEntity<MyResponse> update(Long id, HealthRecordFormDTO healthRecordFormDTO);

    ResponseEntity<MyResponse> remove(Long id);

}
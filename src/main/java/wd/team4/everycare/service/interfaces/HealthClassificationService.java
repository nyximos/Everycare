package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.response.MyResponse;

public interface HealthClassificationService {
    ResponseEntity<MyResponse> getAll();

    ResponseEntity<MyResponse> get(Long id);

    ResponseEntity<MyResponse> save(String name);

    ResponseEntity<MyResponse> update(Long id, String name);

    ResponseEntity<MyResponse> remove(Long id);
}
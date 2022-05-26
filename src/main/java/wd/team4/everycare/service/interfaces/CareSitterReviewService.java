package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.response.MyResponse;

public interface CareSitterReviewService {
    ResponseEntity<MyResponse> getAllCategory(Long id);

    ResponseEntity<MyResponse> getAll();

    ResponseEntity<MyResponse> get(Long id);
}

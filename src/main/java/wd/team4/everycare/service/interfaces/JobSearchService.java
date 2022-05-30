package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.response.MyResponse;

public interface JobSearchService {
    ResponseEntity<MyResponse> findAllJobSearch();
    ResponseEntity<MyResponse> findDetailJobSearch(Long id);
}

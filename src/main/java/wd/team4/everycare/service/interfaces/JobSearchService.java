package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.config.auth.PrincipalDetails;
import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobSearchDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobSearchDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.util.List;

public interface JobSearchService {
    ResponseEntity<MyResponse> findAllJobSearch(PrincipalDetails principalDetails);
    DetailJobSearchDTO findDetailJobSearch(Long id);
}

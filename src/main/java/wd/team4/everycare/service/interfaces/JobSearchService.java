package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobSearchDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobSearchDTO;

import java.util.List;

public interface JobSearchService {
    List<JobSearchDTO> findAllJobSearch(PrincipalDetails principalDetails);
    DetailJobSearchDTO findDetailJobSearch(Long id);
}

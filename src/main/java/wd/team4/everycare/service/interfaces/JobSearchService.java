package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.dto.response.MyResponse;

import java.util.List;

public interface JobSearchService {
    List<CareSitter> findAllJobSearch();
    CareSitter findJobSearch();
    CareSitter findDetailJobSearch(String memberId);
}

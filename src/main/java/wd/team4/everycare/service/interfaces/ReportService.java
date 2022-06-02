package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.report.ReportFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

public interface ReportService {

    ResponseEntity<MyResponse> saveCareReports(PrincipalDetails principalDetails, ReportFormDTO reportForm);

    ResponseEntity<MyResponse> saveBoardReports(PrincipalDetails principalDetails, ReportFormDTO reportForm);

    ResponseEntity<MyResponse> getAll();

    ResponseEntity<MyResponse> stop(String id);

    ResponseEntity<MyResponse> active(String id);

    ResponseEntity<MyResponse> getAllContracts();

    ResponseEntity<MyResponse> getAllReviews();

    ResponseEntity<MyResponse> getAllReviewsByStatus(int status);

    ResponseEntity<MyResponse> getAllContractsByStatus(int status);
}

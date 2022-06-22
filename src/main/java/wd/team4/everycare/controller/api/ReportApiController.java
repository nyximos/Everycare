package wd.team4.everycare.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.report.ReportFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.ReportServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReportApiController {

    private final ReportServiceImpl reportService;

    @PostMapping("/reports/care/member")
    public ResponseEntity<MyResponse> saveCareReport(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                     @ModelAttribute ReportFormDTO reportForm){
        ResponseEntity<MyResponse> responseEntity = reportService.saveCareReports(principalDetails, reportForm);
        return responseEntity;
    }

    @PostMapping("/reports/care/sitter")
    public ResponseEntity<MyResponse> saveCareSitterReport(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                     @ModelAttribute ReportFormDTO reportForm){
        ResponseEntity<MyResponse> responseEntity = reportService.saveCareSitterReports(principalDetails, reportForm);
        return responseEntity;
    }

    @PostMapping("/reports/board")
    public ResponseEntity<MyResponse> saveCareboard(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                    @ModelAttribute ReportFormDTO reportForm) {
        ResponseEntity<MyResponse> responseEntity = reportService.saveBoardReports(principalDetails, reportForm);
        return responseEntity;
    }

    @GetMapping("/admin/reports")
    public ResponseEntity<MyResponse> getAll() {
        ResponseEntity<MyResponse> responseEntity = reportService.getAll();
        return responseEntity;
    }

    @GetMapping("/admin/reports/reviews")
    public ResponseEntity<MyResponse> getAllReviews() {
        ResponseEntity<MyResponse> responseEntity = reportService.getAllReviews();
        return responseEntity;
    }

    @GetMapping("/admin/reports/reviews/status")
    public ResponseEntity<MyResponse> getAllComletionReviews(@RequestParam("status") int status) {
        ResponseEntity<MyResponse> responseEntity = reportService.getAllReviewsByStatus(status);
        return responseEntity;
    }

    @GetMapping("/admin/reports/contracts")
    public ResponseEntity<MyResponse> getAllContracts() {
        ResponseEntity<MyResponse> responseEntity = reportService.getAllContracts();
        return responseEntity;
    }

    @GetMapping("/admin/reports/contracts/status")
    public ResponseEntity<MyResponse> getAllCompletionContracts(@RequestParam("status") int status) {
        ResponseEntity<MyResponse> responseEntity = reportService.getAllContractsByStatus(status);
        return responseEntity;
    }

    @PatchMapping("/admin/members/{id}/activites/stop")
    public ResponseEntity<MyResponse> stop(@PathVariable String id) {
        ResponseEntity<MyResponse> responseEntity = reportService.stop(id);
        return responseEntity;
    }

    @PatchMapping("/admin/members/{id}/activites/active")
    public ResponseEntity<MyResponse> active(@PathVariable String id) {
        ResponseEntity<MyResponse> responseEntity = reportService.active(id);
        return responseEntity;
    }

}

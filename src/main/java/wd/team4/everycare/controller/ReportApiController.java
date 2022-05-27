package wd.team4.everycare.controller;

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

    @PostMapping("/reports/care")
    public ResponseEntity<MyResponse> saveCareReport(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                     @ModelAttribute ReportFormDTO reportForm){
        ResponseEntity<MyResponse> responseEntity = reportService.saveCareReports(principalDetails, reportForm);
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

    @GetMapping("/admin/reports/complitions")
    public ResponseEntity<MyResponse> getAllByComplition() {
        ResponseEntity<MyResponse> responseEntity = reportService.getCompletion();
        return responseEntity;
    }

    @GetMapping("/admin/reports/hold")
    public ResponseEntity<MyResponse> getAllByStatusHold() {
        ResponseEntity<MyResponse> responseEntity = reportService.getHold();
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

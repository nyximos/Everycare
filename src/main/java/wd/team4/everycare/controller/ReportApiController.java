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

    // 목록 조회 - 관리자

    // 활동 정지

    // 활동 재개

}

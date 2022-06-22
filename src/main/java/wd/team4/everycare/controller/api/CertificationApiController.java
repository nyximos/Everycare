package wd.team4.everycare.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.CertificationFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.CertificationServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CertificationApiController {

    private final CertificationServiceImpl certificationService;

    @GetMapping("/dashboard/caresitter/{id}/certifications")
    public ResponseEntity<MyResponse> getAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> responseEntity = certificationService.getAll(principalDetails);
        return responseEntity;
    }

    @PostMapping("/dashboard/caresitter/{id}/certifications")
    public ResponseEntity<MyResponse> saveCertification(
            @ModelAttribute CertificationFormDTO certificationFormDTO) throws IOException {

        ResponseEntity<MyResponse> responseEntity = certificationService.save(certificationFormDTO);
        return responseEntity;
    }

}

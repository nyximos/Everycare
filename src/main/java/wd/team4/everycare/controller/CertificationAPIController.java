package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.dto.CertificationFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.CertificationServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CertificationAPIController {

    private final CertificationServiceImpl certificationService;

    @PostMapping("/dashboard/caresitter/{id}/certifications")
    public ResponseEntity<MyResponse> saveCertification(
            @ModelAttribute CertificationFormDTO certificationFormDTO) throws IOException {

        ResponseEntity<MyResponse> responseEntity = certificationService.save(certificationFormDTO);
        return responseEntity;
    }

}

package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.dto.CertificationFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CertificationAPIController {


//    @PostMapping("/api/dashboard/caresitter/{id}/certifications")
//    public ResponseEntity<MyResponse> saveCertification(
//            @ModelAttribute CertificationFormDTO certificationFormDTO){
//
//    }

}

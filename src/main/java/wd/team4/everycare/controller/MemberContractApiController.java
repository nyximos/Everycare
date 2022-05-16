package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.JobOfferServiceImpl;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberContractApiController {

    private final JobOfferServiceImpl jobOfferService;

    @GetMapping("/dashboard/recruitions/{id}/caresitters")
    public ResponseEntity<MyResponse> findOffer(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> offer = jobOfferService.findOffer(id, principalDetails);
        return offer;
    }

}

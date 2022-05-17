package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.JobOfferServiceImpl;
import wd.team4.everycare.service.MemberContractServiceImpl;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberContractApiController {

    private final JobOfferServiceImpl jobOfferService;
    private final MemberContractServiceImpl memberContractService;


    @GetMapping("/dashboard/recruitions/{id}/caresitters")
    public ResponseEntity<MyResponse> findOffer(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> offer = jobOfferService.findOffer(id, principalDetails);
        return offer;
    }
  
    @PostMapping("/caresitters/{careSitterId}/contracts")
    public ResponseEntity<MyResponse> postMemberContract(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                         @RequestParam("jobOfferId") Long jobOfferId,
                                                         @RequestParam("careSitterId") Long careSitterId){
        ResponseEntity<MyResponse> responseEntity = memberContractService.saveContract(principalDetails, jobOfferId, careSitterId);
        return responseEntity;
    }

}

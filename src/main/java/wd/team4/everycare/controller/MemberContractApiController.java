package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.MemberContractServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberContractApiController {

    private final MemberContractServiceImpl memberContractService;

    @DeleteMapping("/api/contracts/{id}")
    public ResponseEntity<MyResponse> terminatedContract(@PathVariable Long id){
        ResponseEntity<MyResponse> responseEntity = memberContractService.removeContract(id);
        return responseEntity;
    }

    @PostMapping("/api/caresitters/{careSitterId}/contracts")
    public ResponseEntity<MyResponse> postMemberContract(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                         @RequestParam("jobOfferId") Long jobOfferId,
                                                         @RequestParam("careSitterId") Long careSitterId){
        ResponseEntity<MyResponse> responseEntity = memberContractService.saveContract(principalDetails, jobOfferId, careSitterId);
        return responseEntity;
    }
}

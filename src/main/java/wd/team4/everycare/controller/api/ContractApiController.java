package wd.team4.everycare.controller.api;

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
public class ContractApiController {

    private final MemberContractServiceImpl memberContractService;

    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<MyResponse> terminatedContract(@PathVariable Long id){
        ResponseEntity<MyResponse> responseEntity = memberContractService.removeContract(id);
        return responseEntity;
    }

    @GetMapping("/dashboard/contracts/payment-history")
    public ResponseEntity<MyResponse> completionContract(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> completeContract = memberContractService.getCompleteContract(principalDetails);
        return completeContract;
    }
}

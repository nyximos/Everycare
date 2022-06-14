package wd.team4.everycare.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.PayResponse;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberContractApiController {

    private final JobOfferServiceImpl jobOfferService;
    private final MemberContractServiceImpl memberContractService;
    private final PaymentServiceImpl paymentService;
    private final CareNoteServiceImpl careNoteService;

    @GetMapping("/dashboard/recruitions/{jobOfferId}/caresitters")
    public ResponseEntity<MyResponse> findOffer(@PathVariable("jobOfferId") Long jobOfferId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> offer = jobOfferService.findOffer(jobOfferId, principalDetails);
        return offer;
    }

  
    @PostMapping("/caresitters/{careSitterId}/contracts")
    public ResponseEntity<MyResponse> postMemberContract(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                         @RequestParam("jobOfferId") Long jobOfferId,
                                                         @RequestParam("careSitterId") Long careSitterId){
        ResponseEntity<MyResponse> responseEntity = memberContractService.saveContract(principalDetails, jobOfferId, careSitterId);
        return responseEntity;
    }

    @GetMapping("/caresitters/{careSitterId}/recruitions")
    public ResponseEntity<MyResponse> getRecruitions(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> responseEntity = memberContractService.getRecruitions(principalDetails);
        return responseEntity;
    }

    @GetMapping("/dashboard/contracts/payments")
    public ResponseEntity<MyResponse> signContract(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount, @RequestParam Long contractId) throws IOException {
        System.out.println("paymentKey = " + paymentKey);
        System.out.println("orderId = " + orderId);
        System.out.println("amount = " + amount);
        System.out.println("contractId = " + contractId);
        PayResponse payment = paymentService.payment(paymentKey, orderId, amount);
        ResponseEntity<MyResponse> signContract = memberContractService.signContract(payment, contractId);
        return signContract;
    }

    @GetMapping("/member/caretargets/{id}/contracts/complition")
    public ResponseEntity<MyResponse> getContracts(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = memberContractService.getContracts(principalDetails,id);
        return responseEntity;
    }

    @GetMapping("/member/caretargets/{caretargetId}/contracts/complition/{contractId}/carenotes")
    public ResponseEntity<MyResponse> getCareNotes(@PathVariable("contractId") Long id) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.getCareNotes(id);
        return responseEntity;
    }

    @GetMapping("/member/caretargets/{caretargetId}/contracts/complition/{contractId}/carenotes/{careNoteId}")
    public ResponseEntity<MyResponse> getCareNote(@PathVariable("careNoteId") Long id) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.getCareNote(id);
        return responseEntity;
    }

}

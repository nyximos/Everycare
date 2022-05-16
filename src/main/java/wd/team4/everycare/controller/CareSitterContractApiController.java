package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.JobOfferServiceImpl;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CareSitterContractApiController {

    private final JobOfferServiceImpl jobOfferService;

    @GetMapping("/dashboard/caresitter/contracts/{contractId}")
    public ResponseEntity<MyResponse> findDetailOffer(@PathVariable("contractId") Long id){
        ResponseEntity<MyResponse> detailOffer = jobOfferService.findDetailOffer(id);
        return detailOffer;
    }

    @PostMapping("/recruitions/{id}/contracts")
    public ResponseEntity<MyResponse> saveContracts(@PathVariable("id")Long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> offer = jobOfferService.offer(id, principalDetails);
        return offer;
    }
}

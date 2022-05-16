package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.MemberContractServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ContractApiController {

    private final MemberContractServiceImpl memberContractService;

    @DeleteMapping("/api/contracts/{id}")
    public ResponseEntity<MyResponse> terminatedContract(@PathVariable Long id){
        ResponseEntity<MyResponse> responseEntity = memberContractService.removeContract(id);
        return responseEntity;
    }
}

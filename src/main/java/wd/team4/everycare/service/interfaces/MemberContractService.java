package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.PayResponse;
import wd.team4.everycare.dto.response.MyResponse;

public interface MemberContractService {

    ResponseEntity<MyResponse> removeContract(Long id);

    ResponseEntity<MyResponse> saveContract(PrincipalDetails principalDetails, Long jobOfferId, Long careSitterId);

    ResponseEntity<MyResponse> signContract(PayResponse payResponse, Long contractId);

    ResponseEntity<MyResponse> getRecruitions(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> getCompleteContract(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> getContracts(PrincipalDetails principalDetails, Long id);
}

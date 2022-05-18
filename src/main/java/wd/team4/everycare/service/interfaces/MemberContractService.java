package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;

public interface MemberContractService {

    ResponseEntity<MyResponse> removeContract(Long id);

    ResponseEntity<MyResponse> saveContract(PrincipalDetails principalDetails, Long jobOfferId, Long careSitterId);

    ResponseEntity<MyResponse> getRecruitions(PrincipalDetails principalDetails);
}

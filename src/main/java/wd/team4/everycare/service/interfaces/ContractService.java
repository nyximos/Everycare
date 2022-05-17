package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;

public interface ContractService {

    ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> get(Long id);
}

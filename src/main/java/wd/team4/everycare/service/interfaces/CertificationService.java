package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.dto.CertificationFormDTO;
import wd.team4.everycare.dto.CertificationViewDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.io.IOException;
import java.util.List;

public interface CertificationService {

    List<CertificationViewDTO> findAllByCareSitter(Long id);

    ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> save(CertificationFormDTO certificationFormDTO) throws IOException;

    List<CertificationViewDTO>  webFindAllThatRequireApproval();

    List<CareSitter> webFindAllByCareSitter();
}

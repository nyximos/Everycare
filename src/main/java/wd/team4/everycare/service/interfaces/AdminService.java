package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.response.MyResponse;

import java.time.LocalDateTime;

public interface AdminService {

    ResponseEntity<MyResponse> registrationAdmin(String id, LocalDateTime time);

    ResponseEntity<MyResponse> approveStore(Long id);

    ResponseEntity<MyResponse> approveCertification(Long id, Member admin);
}

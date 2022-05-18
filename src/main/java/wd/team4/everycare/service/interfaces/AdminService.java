package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.response.MyResponse;

import java.time.LocalDateTime;

public interface AdminService {

    ResponseEntity<MyResponse> approveAdmin(String id);

    ResponseEntity<MyResponse> removeAdmin(String id);

    ResponseEntity<MyResponse> approveStore(Long id);

    ResponseEntity<MyResponse> getStores();

    ResponseEntity<MyResponse> getStore(Long id);

    ResponseEntity<MyResponse> getCertifications();

    ResponseEntity<MyResponse> getCertification(Long id);

    ResponseEntity<MyResponse> approveCertification(Long id, Member admin);

}

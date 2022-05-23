package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.member.MemberScheduleFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

public interface MemberScheduleService {

    ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> get(Long id);

    ResponseEntity<MyResponse> save(PrincipalDetails principalDetails, MemberScheduleFormDTO memberScheduleFormDTO);

    ResponseEntity<MyResponse> update(Long id, MemberScheduleFormDTO memberScheduleFormDTO);

    ResponseEntity<MyResponse> remove(Long id);
}
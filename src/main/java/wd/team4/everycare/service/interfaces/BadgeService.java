package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;

public interface BadgeService {

    ResponseEntity<MyResponse> giveBadge(PrincipalDetails principalDetails);

    ResponseEntity<MyResponse> findBadge(PrincipalDetails principalDetails);
}

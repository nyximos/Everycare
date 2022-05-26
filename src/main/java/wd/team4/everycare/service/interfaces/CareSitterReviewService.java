package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.careSitterReview.CareSitterReviewFormDTO;
import wd.team4.everycare.dto.careSitterReview.CareSitterReviewUpdateFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

public interface CareSitterReviewService {
    ResponseEntity<MyResponse> getAllCategory(Long id);

    ResponseEntity<MyResponse> getAll();

    ResponseEntity<MyResponse> get(Long id);

    ResponseEntity<MyResponse> save(@AuthenticationPrincipal PrincipalDetails principalDetails, Long id, CareSitterReviewFormDTO careSitterReviewFormDTO);

    ResponseEntity<MyResponse> update(Long id, CareSitterReviewUpdateFormDTO careSitterReviewUpdateFormDTO);

    ResponseEntity<MyResponse> remove(Long id);
}

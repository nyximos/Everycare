package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.careSitterReview.CareSitterReviewFormDTO;
import wd.team4.everycare.dto.careSitterReview.CareSitterReviewUpdateFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.CareSitterReviewServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareSitterReviewApiController {

    private final CareSitterReviewServiceImpl careSitterReviewService;

    @GetMapping("/carenote/{id}/categories")
    public ResponseEntity<MyResponse> getAllCategory(@PathVariable("id") Long id){
        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.getAllCategory(id);
        return responseEntity;
    }

    @GetMapping("/carenote/{id}/reviews")
    public ResponseEntity<MyResponse> getAll(){
        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.getAll();
        return responseEntity;
    }

    @GetMapping("/carenote/{carenoteId}/review/{reviewId}")
    public ResponseEntity<MyResponse> get(@PathVariable("reviewId") Long id){
        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.get(id);
        return responseEntity;
    }

    @PostMapping("/api/carenote/{id}/reviews")
    public ResponseEntity<MyResponse> save(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                           @PathVariable("id") Long id,
                                           @ModelAttribute CareSitterReviewFormDTO careSitterReviewFormDTO){
        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.save(principalDetails, id, careSitterReviewFormDTO);
        return responseEntity;
    }

    @PatchMapping("/carenote/{carenoteId}/review/{reviewId}")
    public ResponseEntity<MyResponse> update(@PathVariable("reviewId") Long id,
                                             @ModelAttribute CareSitterReviewUpdateFormDTO careSitterReviewUpdateFormDTO){
        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.update(id, careSitterReviewUpdateFormDTO);
        return responseEntity;
    }

    @DeleteMapping("/carenote/{carenoteId}/review/{reviewId}")
    public ResponseEntity<MyResponse> remove(@PathVariable("reviewId") Long id){
        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.remove(id);
        return responseEntity;
    }

}

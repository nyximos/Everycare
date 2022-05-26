package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.CareSitterReviewServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareSitterReviewApiController {

    private final CareSitterReviewServiceImpl careSitterReviewService;

    // 카테고리 조회
    @GetMapping("/carenote/{id}/categories")
    public ResponseEntity<MyResponse> getAllCategory(@PathVariable("id") Long id){
        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.getAllCategory(id);
        return responseEntity;
    }

    // 목록 조회
    @GetMapping("/carenote/{id}/reviews")
    public ResponseEntity<MyResponse> getAll(){
        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.getAll();
        return responseEntity;
    }

    // 상세 조회
    @GetMapping("/carenote/{carenoteId}/review/{reviewId}")
    public ResponseEntity<MyResponse> get(@PathVariable("reviewId") Long id){
        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.get(id);
        return responseEntity;
    }

//    // 케어시터 활동 후기 등록
//    @PostMapping("/api/carenote/{id}/reviews")
//    public ResponseEntity<MyResponse> save(){
//        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.save();
//        return responseEntity;
//    }
//
//
//    // 케어시터 활동 후기 수정
//    @PatchMapping("/carenote/{carenoteId}/review/{reviewId}")
//    public ResponseEntity<MyResponse> update(){
//        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.update();
//        return responseEntity;
//    }
//
//
//    // 케어시터 활동 후기 삭제
//    @DeleteMapping("/carenote/{carenoteId}/review/{reviewId}")
//    public ResponseEntity<MyResponse> remove(){
//        ResponseEntity<MyResponse> responseEntity = careSitterReviewService.remove();
//        return responseEntity;
//    }

}

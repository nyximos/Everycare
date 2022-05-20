package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.careSitter.CareSitterFormDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobSearchDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobSearchDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.CareSitterServiceImpl;
import wd.team4.everycare.service.JobSearchServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareSitterApiController {

    private final CareSitterServiceImpl careSitterService;
    private final JobSearchServiceImpl jobSearchService;

// <<<<<<< HEAD
// @GetMapping("/caresitters")
//     public ResponseEntity<MyResponse> findJobSearch(Model model) {
//         List<CareSitter> allJobSearch = jobSearchService.findAllJobSearch();

//         MyResponse<Object> body = MyResponse.builder()
//                 .header(StatusEnum.OK)
//                 .body(allJobSearch)
//                 .message("ok").
//                 build();
//         HttpHeaders headers = new HttpHeaders();
//         return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
//     }
//     @GetMapping("/caresitters/{id}")
//     public ResponseEntity<MyResponse> getDetailJobSearch(@PathVariable("id") String id, Model model){

//         CareSitter detailJobSearch = jobSearchService.findDetailJobSearch(id);
//         MyResponse body = MyResponse.builder()
//                 .header(StatusEnum.OK)
//                 .body(detailJobSearch)
//                 .message("ok")
//                 .build();

//         return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
//     }
//     @PostMapping("/dashboard/caresitter")
// =======

    @GetMapping("/caresitters")
    public ResponseEntity<MyResponse> findJobSearch() {
        List<JobSearchDTO> all = jobSearchService.findAllJobSearch();
        MyResponse<Object> body = MyResponse.builder()
                .header(StatusEnum.OK)
                .body(all)
                .message("ok").
                build();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @GetMapping("/caresitters/{id}")
    public ResponseEntity<MyResponse> getDetailJobSearch(@PathVariable("id") Long id, Model model){

        DetailJobSearchDTO detailJobSearch = jobSearchService.findDetailJobSearch(id);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .body(detailJobSearch)
                .message("ok")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    // @GetMapping("/dashboard/caresitter")
    // public ResponseEntity<MyResponse> getCaresitter(@AuthenticationPrincipal PrincipalDetails principalDetails){
    //     ResponseEntity<MyResponse> responseEntity = careSitterService.findCareSitterByMember(principalDetails);
    //     return responseEntity;
    // }

    @PostMapping("/dashboard/caresitter")
    public ResponseEntity<MyResponse> saveCareSitter(
            @ModelAttribute CareSitterFormDTO careSitterFormDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        careSitterFormDTO.setCreatedAt(time);
        careSitterFormDTO.setUpdatedAt(time);
        careSitterFormDTO.setName(principalDetails.getUser().getName());

        Member user = principalDetails.getUser();
        careSitterFormDTO.setMember(user);

        Long careSitterId = careSitterService.save(careSitterFormDTO);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .body(careSitterId)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @PatchMapping("/dashboard/caresitter/{id}")
    public ResponseEntity<MyResponse> patchCareSitter(
            @PathVariable("id") Long id,
            @ModelAttribute CareSitterFormDTO careSitterFormDTO
    ){
        careSitterService.update(id, careSitterFormDTO);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
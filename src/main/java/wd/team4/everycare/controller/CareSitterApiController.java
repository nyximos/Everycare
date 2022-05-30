package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.MultipartFileDTO;
import wd.team4.everycare.dto.careSitter.CareSitterFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.CareSitterServiceImpl;
import wd.team4.everycare.service.JobSearchServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareSitterApiController {

    private final CareSitterServiceImpl careSitterService;
    private final JobSearchServiceImpl jobSearchService;


    @GetMapping("/caresitters")
    public ResponseEntity<MyResponse> findJobSearch() {
        ResponseEntity<MyResponse> responseEntity = jobSearchService.findAllJobSearch();
        return responseEntity;
    }

    @GetMapping("/caresitters/{id}")
    public ResponseEntity<MyResponse> getDetailJobSearch(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> detailJobSearch = jobSearchService.findDetailJobSearch(id);
        return detailJobSearch;
    }

    @GetMapping("/dashboard/caresitter")
    public ResponseEntity<MyResponse> getCaresitter(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> responseEntity = careSitterService.findCareSitterByMember(principalDetails);
        return responseEntity;
    }

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

    @PostMapping("/dashboard/caresitter/{id}/image")
    public ResponseEntity<MyResponse> saveImage(@PathVariable Long id, @ModelAttribute MultipartFileDTO imageDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = careSitterService.saveImage(id,imageDTO);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/caresitter/{id}/image/{imageId}")
    public ResponseEntity<MyResponse> removeImage(@PathVariable("imageId") Long id){
        ResponseEntity<MyResponse> responseEntity = careSitterService.removeImage(id);
        return responseEntity;
    }

    @GetMapping("/caresitters/region")
    public ResponseEntity<MyResponse> getAllByRegion(@RequestParam("region") String region) {
        ResponseEntity<MyResponse> responseEntity = careSitterService.getAllByRegion(region);
        return responseEntity;
    }

    @GetMapping("/caresitters/caretype")
    public ResponseEntity<MyResponse> getAllByCareType(@RequestParam("caretype") String caretype) {
        ResponseEntity<MyResponse> responseEntity = careSitterService.getAllByCareType(caretype);
        return responseEntity;
    }

    @GetMapping("/caresitters/time")
    public ResponseEntity<MyResponse> getAllByTime(@RequestParam("time") String time) {
        ResponseEntity<MyResponse> responseEntity = careSitterService.getAllByTime(time);
        return responseEntity;
    }
}
package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.CareSitterFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.CareSitterServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareSitterApiController {

    private final CareSitterServiceImpl careSitterService;

    @PostMapping("/dashboard/caresitter")

    public ResponseEntity<MyResponse> saveCareSitter(
            @ModelAttribute CareSitterFormDTO careSitterFormDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) throws IOException {
        LocalDateTime time = LocalDateTime.now();
        careSitterFormDTO.setCreatedAt(time);
        careSitterFormDTO.setUpdatedAt(time);

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
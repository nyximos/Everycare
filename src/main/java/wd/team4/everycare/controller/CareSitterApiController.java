package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.CareSitterFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.CareSitterServiceImpl;
import wd.team4.everycare.service.FileStoreService;

import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareSitterApiController {

    private final CareSitterServiceImpl careSitterService;
    private final MemberRepository memberRepository;

    @ResponseBody
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

        careSitterService.save(careSitterFormDTO);

        MyResponse<CareSitterFormDTO> body = MyResponse.<CareSitterFormDTO>builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .body(careSitterFormDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    /*
    @ResponseBody
    @PutMapping("/dashboard/caresitter")
    public ResponseEntity<MyResponse> putCareSitter(
            @RequestBody CareSitterDTO careSitterDTO
    ){
        LocalDateTime time = LocalDateTime.now();
        careSitterService.
    }

     */

}
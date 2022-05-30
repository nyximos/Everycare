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
import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.service.CareTargetServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareTargetApiController {

    private final CareTargetServiceImpl careTargetService;
    private final CareTargetRepository careTargetRepository;

    @GetMapping("/dashboard/caretargets")
    public ResponseEntity<MyResponse> getCareTargets(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> responseEntity = careTargetService.findAll(principalDetails);
        return responseEntity;
    }

    @GetMapping("/dashboard/caretargets/{id}")
    public ResponseEntity<MyResponse> getCareTarget(@PathVariable Long id){
        ResponseEntity<MyResponse> responseEntity = careTargetService.findById(id);
        return responseEntity;
    }

    @PostMapping("/dashboard/caretargets/new")
    public ResponseEntity<MyResponse> saveCareTarget(
            @ModelAttribute CareTargetFormDTO careTargetFormDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) throws IOException {

        Member user = principalDetails.getUser();
        careTargetFormDTO.setMember(user);

        Long careTagetId = careTargetService.save(careTargetFormDTO);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careTagetId)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    @PatchMapping("/dashboard/caretargets/{id}")
    public ResponseEntity<MyResponse> patchCareTarget(
            @PathVariable("id") Long id,
            @ModelAttribute CareTargetFormDTO careTargetFormDTO
    ){
        careTargetService.update(id, careTargetFormDTO);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @DeleteMapping("/dashboard/caretargets/{id}")
    public ResponseEntity<MyResponse> deleteCareTarget(@PathVariable("id") Long id ){
        careTargetRepository.deleteById(id);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @PostMapping("/dashboard/caretargets/{id}/image")
    public ResponseEntity<MyResponse> saveImage(@PathVariable Long id, @ModelAttribute MultipartFileDTO imageDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = careTargetService.saveImage(id,imageDTO);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/caretargets/{id}/image/{imageId}")
    public ResponseEntity<MyResponse> removeImage(@PathVariable("imageId") Long id){
        ResponseEntity<MyResponse> responseEntity = careTargetService.removeImage(id);
        return responseEntity;
    }
}
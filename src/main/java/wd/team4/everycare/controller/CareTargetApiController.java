package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.CareTargetFormDTO;
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

    @ResponseBody
    @PostMapping("/carenote/caretargets/new")
    public ResponseEntity<MyResponse> saveCareTarget(
            @ModelAttribute CareTargetFormDTO careTargetFormDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) throws IOException {
        
        Member user = principalDetails.getUser();
        careTargetFormDTO.setMember(user);

        careTargetService.save(careTargetFormDTO);

        MyResponse<CareTargetFormDTO> body = MyResponse.<CareTargetFormDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careTargetFormDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @ResponseBody
    @PatchMapping("/carenote/caretargets/{id}")
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

    @DeleteMapping("/carenote/caretargets/{id}")
    public ResponseEntity<MyResponse> deleteCareTarget(@PathVariable("id") Long id ){
        careTargetRepository.deleteById(id);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}

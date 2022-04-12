package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.StoreFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.StoreServiceImpl;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreServiceImpl storeService;

    @PostMapping("/store")
    public ResponseEntity<MyResponse> createStore(
            @ModelAttribute StoreFormDTO storeFormDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){

        Member member = principalDetails.getUser();
        storeFormDTO.setMember(member);

        storeService.save(storeFormDTO);

        MyResponse<StoreFormDTO> body = MyResponse.<StoreFormDTO>builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .body(storeFormDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}

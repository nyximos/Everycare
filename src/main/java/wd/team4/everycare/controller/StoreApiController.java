package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.StoreFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.StoreRepository;
import wd.team4.everycare.service.StoreServiceImpl;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreServiceImpl storeService;
    private final StoreRepository storeRepository;

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

    @DeleteMapping("/store/{id}")
    public ResponseEntity<MyResponse> deletesStore(@PathVariable("id") Long id){
        storeRepository.deleteById(id);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}

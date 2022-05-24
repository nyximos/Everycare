package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.MemberCareNoteServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberCareNoteApiController {

    private final MemberCareNoteServiceImpl careNoteService;

    @GetMapping("/member/carenotes")
    public ResponseEntity<MyResponse> getAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.getAll(principalDetails);
        return responseEntity;
    }
}
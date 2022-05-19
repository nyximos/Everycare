package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.careNote.CareNoteImageDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.CareNoteServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareNoteApiController {

    private final CareNoteServiceImpl careNoteService;

    @GetMapping("/carenotes")
    public ResponseEntity<MyResponse> getAll(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> responseEntity = careNoteService.getAll(principalDetails);
        return responseEntity;
    }

    @PatchMapping("/carenotes/{id}/photo")
    public ResponseEntity<MyResponse> start(@ModelAttribute CareNoteImageDTO imageDTO, @PathVariable("id") Long id) throws IOException {
        ResponseEntity<MyResponse> responseEntity = careNoteService.start(id, imageDTO);
        return responseEntity;
    }

}

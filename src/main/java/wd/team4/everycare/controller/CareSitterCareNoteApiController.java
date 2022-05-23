package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.careNote.CareNoteImageDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.CareSitterCareNoteServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareSitterCareNoteApiController {

    private final CareSitterCareNoteServiceImpl careNoteService;

    @GetMapping("/carenotes")
    public ResponseEntity<MyResponse> getAll(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> responseEntity = careNoteService.getAll(principalDetails);
        return responseEntity;
    }

    @GetMapping("/carenotes/{id}")
    public ResponseEntity<MyResponse> get(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.get(id);
        return responseEntity;
    }

    @PatchMapping("/carenotes/{id}/photo")
    public ResponseEntity<MyResponse> start(@PathVariable("id") Long id, @ModelAttribute CareNoteImageDTO imageDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = careNoteService.start(id, imageDTO);
        return responseEntity;
    }

    @GetMapping("/carenotes/{id}/schedules")
    public ResponseEntity<MyResponse> getSchedule(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.getSchedule(id);
        return responseEntity;
    }

    @PatchMapping("/api/carenote/{carenoteId}/schedules/{activityId}/content")
    public ResponseEntity<MyResponse> saveContent(@PathVariable("activityId") Long id, @RequestParam("content") String content) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.saveContent(id, content);
        return responseEntity;
    }

    @PatchMapping("/api/carenote/{carenoteId}/schedules/{activityId}/photo")
    public ResponseEntity<MyResponse> savePhoto(@PathVariable("activityId") Long id, @ModelAttribute CareNoteImageDTO imageDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = careNoteService.savePhoto(id, imageDTO);
        return responseEntity;
    }

    @DeleteMapping("/api/carenote/{carenoteId}/schedules/{activityId}/content")
    public ResponseEntity<MyResponse> removeContent(@PathVariable("activityId") Long id) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.removeContent(id);
        return responseEntity;
    }

    @DeleteMapping("/api/carenote/{carenoteId}/schedules/{activityId}/photo")
    public ResponseEntity<MyResponse> removePhoto(@PathVariable("activityId") Long id) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.removePhoto(id);
        return responseEntity;
    }

    @PatchMapping("/api/carenotes/{id}/schedules/complition")
    public ResponseEntity<MyResponse> complete(@PathVariable("activityId") Long id) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.complete(id);
        return responseEntity;
    }
}
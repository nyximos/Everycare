package wd.team4.everycare.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.careNote.CareNoteImageDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.CareNoteServiceImpl;
import wd.team4.everycare.service.CareSitterCareNoteServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareSitterCareNoteApiController {

    private final CareSitterCareNoteServiceImpl careSitterCareNoteService;
    private final CareNoteServiceImpl careNoteService;

    @GetMapping("/carenotes")
    public ResponseEntity<MyResponse> getAll(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.getAll(principalDetails);
        return responseEntity;
    }

    @GetMapping("/carenotes/{id}")
    public ResponseEntity<MyResponse> get(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.get(id);
        return responseEntity;
    }

    @PatchMapping("/carenotes/{id}/photo")
    public ResponseEntity<MyResponse> start(@PathVariable("id") Long id, @ModelAttribute CareNoteImageDTO imageDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.start(id, imageDTO);
        return responseEntity;
    }

    @GetMapping("/carenotes/{id}/schedules")
    public ResponseEntity<MyResponse> getSchedule(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.getSchedule(id);
        return responseEntity;
    }

    @PatchMapping("/carenote/{carenoteId}/schedules/{activityId}/content")
    public ResponseEntity<MyResponse> saveContent(@PathVariable("activityId") Long id, @RequestParam("content") String content) {
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.saveContent(id, content);
        return responseEntity;
    }

    @PatchMapping("/carenote/{carenoteId}/schedules/{activityId}/photo")
    public ResponseEntity<MyResponse> savePhoto(@PathVariable("activityId") Long id, @ModelAttribute CareNoteImageDTO imageDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.savePhoto(id, imageDTO);
        return responseEntity;
    }

    @DeleteMapping("/carenote/{carenoteId}/schedules/{activityId}/content")
    public ResponseEntity<MyResponse> removeContent(@PathVariable("activityId") Long id) {
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.removeContent(id);
        return responseEntity;
    }

    @DeleteMapping("/carenote/{carenoteId}/schedules/{activityId}/photo")
    public ResponseEntity<MyResponse> removePhoto(@PathVariable("activityId") Long id) {
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.removePhoto(id);
        return responseEntity;
    }

    @PatchMapping("/carenotes/{id}/schedules/complition")
    public ResponseEntity<MyResponse> complete(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.complete(id);
        return responseEntity;
    }

    @GetMapping("/carenote/contracts/completion")
    public ResponseEntity<MyResponse> getContracts(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> responseEntity = careSitterCareNoteService.getContracts(principalDetails);
        return responseEntity;
    }

    @GetMapping("/carenote/contracts/completion/{id}")
    public ResponseEntity<MyResponse> getCareNotes(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.getCareNotes(id);
        return responseEntity;
    }

    @GetMapping("/carenote/contracts/completion/{contractId}/carenotes/{careNoteId}")
    public ResponseEntity<MyResponse> approve(@PathVariable("careNoteId") Long id) {
        ResponseEntity<MyResponse> responseEntity = careNoteService.getCareNote(id);
        return responseEntity;
    }
}

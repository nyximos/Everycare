package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.member.MemberScheduleFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.MemberScheduleServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberScheduleApiController {

    private final MemberScheduleServiceImpl memberScheduleService;

    @GetMapping("/dashboard/calendar")
    public ResponseEntity<MyResponse> getAll(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        ResponseEntity<MyResponse> responseEntity = memberScheduleService.getAll(principalDetails);
        return responseEntity;
    }

    @GetMapping("/dashboard/calendar/{id}")
    public ResponseEntity<MyResponse> get(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = memberScheduleService.get(id);
        return responseEntity;
    }

    @PostMapping("/dashboard/calendar")
    public ResponseEntity<MyResponse> save(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                           @ModelAttribute MemberScheduleFormDTO memberScheduleFormDTOe) {
        ResponseEntity<MyResponse> responseEntity = memberScheduleService.save(principalDetails, memberScheduleFormDTOe);
        return responseEntity;
    }

    @PatchMapping("/dashboard/calendar/{id}")
    public ResponseEntity<MyResponse> update(@PathVariable("id") Long id,
                                             @ModelAttribute MemberScheduleFormDTO memberScheduleFormDTO) {
        ResponseEntity<MyResponse> responseEntity = memberScheduleService.update(id, memberScheduleFormDTO);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/calendar/{id}")
    public ResponseEntity<MyResponse> remove(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = memberScheduleService.remove(id);
        return responseEntity;
    }


}
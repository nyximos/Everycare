package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.board.BoardDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.AdminServiceImpl;
import wd.team4.everycare.service.MemberServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApiController {

    private final AdminServiceImpl adminService;
    private final MemberRepository memberRepository;
    private final MemberServiceImpl memberService;

    @GetMapping("/members")
    public ResponseEntity<MyResponse> getMembers() {
        ResponseEntity<MyResponse> responseEntity = memberService.getAll();
        return responseEntity;
    }

    @PostMapping("/members/{id}")
    public ResponseEntity<MyResponse> postAdmin(@PathVariable("id") String id) {
        ResponseEntity<MyResponse> responseEntity = adminService.approveAdmin(id);
        return responseEntity;
    }

    @PatchMapping("/members/{id}")
    public ResponseEntity<MyResponse> removeAdmin(@PathVariable("id") String id) {
        ResponseEntity<MyResponse> responseEntity = adminService.removeAdmin(id);
        return responseEntity;
    }


    @GetMapping("/stores")
    public ResponseEntity<MyResponse> getStores() {
        ResponseEntity<MyResponse> responseEntity = adminService.getStores();
        return responseEntity;
    }

    @GetMapping("stores/{id}")
    public ResponseEntity<MyResponse> getStore(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = adminService.getStore(id);
        return responseEntity;
    }

    @PostMapping("/stores/{id}")
    public ResponseEntity<MyResponse> postStore(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = adminService.approveStore(id);
        return responseEntity;
    }

    @GetMapping("/caresitters/certifications")
    public ResponseEntity<MyResponse> getAll() {
        ResponseEntity<MyResponse> responseEntity = adminService.getCertifications();
        return responseEntity;
    }

    @GetMapping("/caresitter-certifications/{id}")
    public ResponseEntity<MyResponse> get(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = adminService.getCertification(id);
        return responseEntity;
    }

    @PostMapping("/certifications/{id}")
    public ResponseEntity<MyResponse> postCertification(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Member admin = principalDetails.getUser();
        ResponseEntity<MyResponse> responseEntity = adminService.approveCertification(id, admin);
        return responseEntity;
    }

    @PostMapping("/faq")
    public ResponseEntity<MyResponse> writeFAQ(@AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute BoardDTO boardDTO){
        ResponseEntity<MyResponse> faq = adminService.writeFAQ(principalDetails, boardDTO);
        return faq;
    }

    @PatchMapping("/faq")
    public ResponseEntity<MyResponse> updateFAQ(@AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute BoardDTO boardDTO){
        ResponseEntity<MyResponse> updateFAQ = adminService.updateFAQ(principalDetails, boardDTO);
        return updateFAQ;
    }

    @DeleteMapping("/faq/{id}")
    public ResponseEntity<MyResponse> removeFAQ(@PathVariable("id")Long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> removeFAQ = adminService.removeFAQ(principalDetails, id);
        return removeFAQ;
    }
}

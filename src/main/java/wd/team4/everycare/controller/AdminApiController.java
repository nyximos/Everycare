package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.AdminServiceImpl;
import wd.team4.everycare.service.MemberServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

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

        LocalDateTime time = LocalDateTime.now();

        Optional<Member> member1 = memberRepository.findById(id);
        Member member = member1.orElse(null);

        adminService.registrationAdmin(id,time);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @GetMapping("/stores")
    public ResponseEntity<MyResponse> getStores() {
        ResponseEntity<MyResponse> responseEntity = adminService.getStores();
        return responseEntity;
    }

    @GetMapping("stores/{id}")
    public ResponseEntity<MyResponse> getStore(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = adminService.get(id);
        return responseEntity;
    }

    @PostMapping("/stores/{id}")
    public ResponseEntity<MyResponse> postStore(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = adminService.approveStore(id);
        return responseEntity;
    }

    @PostMapping("/certifications/{id}")
    public ResponseEntity<MyResponse> postCertification(@PathVariable("id") Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Member admin = principalDetails.getUser();
        ResponseEntity<MyResponse> responseEntity = adminService.approveCertification(id, admin);
        return responseEntity;
    }
}

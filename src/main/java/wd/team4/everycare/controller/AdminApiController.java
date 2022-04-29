package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.AdminServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApiController {

    private final AdminServiceImpl adminService;
    private final MemberRepository memberRepository;

    @PostMapping("/members/{id}")
    public ResponseEntity<MyResponse> postAdmin(@PathVariable("id") String id){


        System.out.println("id = " + id);
        LocalDateTime time = LocalDateTime.now();

        Optional<Member> member1 = memberRepository.findById(id);
        System.out.println("member1 = " + member1);
        Member member = member1.orElse(null);
        System.out.println("member = " + member);

        adminService.registrationAdmin(id,time);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @PostMapping("/stores/{id}")
    public ResponseEntity<MyResponse> postStore(@PathVariable("id") Long id){
        ResponseEntity<MyResponse> responseEntity = adminService.approveStore(id);
        return responseEntity;
    }
}

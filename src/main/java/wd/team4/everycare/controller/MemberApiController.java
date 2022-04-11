package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.ActivityStatus;
import wd.team4.everycare.domain.MemberRole;
import wd.team4.everycare.dto.member.SignupDTO;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.MemberServiceImpl;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
// @CrossOrigin(origins = "http://localhost:8087"*/32 m)  // CORS 허용
public class MemberApiController {

    private final MemberRepository memberRepository;
    private final MemberServiceImpl memberService;

    // Tip : JWT를 사용하면 UserDetailsService를 호출하지 않기 때문에 @AuthenticationPrincipal 사용 불가능.
    // 왜냐하면 @AuthenticationPrincipal은 UserDetailsService에서 리턴될 때 만들어지기 때문이다.

    // 유저 혹은 매니저 혹은 어드민이 접근 가능
    @GetMapping("/user")
    public String user(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("principal : "+principal.getUser().getId());
        System.out.println("principal : "+principal.getUser().getPassword());

        return "<h1>user</h1>";
    }

    @PostMapping("/signup")
    public String join(@Valid @RequestBody SignupDTO signupDTO) {
        System.out.println("in");
        LocalDateTime time = LocalDateTime.now();
        signupDTO.setCreatedAt(time);
        signupDTO.setActivityStatus(ActivityStatus.ACTIVE);
        signupDTO.setRole(MemberRole.ROLE_MEMBER);


        memberService.join(signupDTO);
        return "회원가입완료";
    }

}





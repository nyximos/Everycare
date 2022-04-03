package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.MemberRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberWebController {

    private final MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    // 모든 사람이 접근 가능
    @GetMapping("/home")
    public String home() {
        return "<h1>home</h1>";
    }

    // 매니저 혹은 어드민이 접근 가능
    @GetMapping("/manager/reports")
    public String reports() {
        return "<h1>reports</h1>";
    }

    // 어드민이 접근 가능
    @GetMapping("/admin/users")
    public List<Member> users(){
        return memberRepository.findAll();
    }
}

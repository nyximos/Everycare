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

    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }

}

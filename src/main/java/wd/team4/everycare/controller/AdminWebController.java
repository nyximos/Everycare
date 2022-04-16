package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.repository.StoreRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminWebController {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/admin/members")
    public String memberManagement(Model model){

        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "admin-members";
    }

    /* TODO
    @GetMapping("/certifications")
    public String certifications(){
        return "admin-certifications";
    }

    @GetMapping("/reports")
    public String reports(){

        return "admin-reports";
    }

    @GetMapping("/stores")
    public String stores(Model model){

        return "admin-stores";
    }
    */

}

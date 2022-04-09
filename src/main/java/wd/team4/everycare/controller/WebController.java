package wd.team4.everycare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(){
        return "all";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
//
//    @GetMapping("/member")
//    public String member(){
//        return "member";
//    }
//
//    @GetMapping("/admin")
//    public String admin(){
//        return "admin";
//    }

}

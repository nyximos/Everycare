package wd.team4.everycare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class MemberController {

    @GetMapping("/test")
    public String test(){
        System.out.println("test");
        return "test";
    }

    @GetMapping("/hello")
    public void hello() {
        System.out.println("HelloWorld");
    }

}
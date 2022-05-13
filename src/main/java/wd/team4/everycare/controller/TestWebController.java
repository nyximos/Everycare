package wd.team4.everycare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestWebController {

    @GetMapping("/test")
    public String testPage(){
        return "joboffer-pay";
    }
}

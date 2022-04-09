package wd.team4.everycare.controller.CareSitter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/caresitter/*")
@RequiredArgsConstructor
public class TestCareSitterWebController {
    @GetMapping("/test")
    public String test(){
        return "CareSitter/test";
    }

    @GetMapping("/test1")
    public String test1(){
        return "CareSitter/test1";
    }
}

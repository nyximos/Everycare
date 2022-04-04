package wd.team4.everycare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.domain.Test;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class TestApiController {

    @ResponseBody
    @GetMapping("/test")
    public Test test(){
        System.out.println("test");
        Test test = new Test();
        test.setId("1");
        test.setName("test");
        return test;
    }

    @GetMapping("/hello")
    public void hello() {
        System.out.println("HelloWorld");
    }

}
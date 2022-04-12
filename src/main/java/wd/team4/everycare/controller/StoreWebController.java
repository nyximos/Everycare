package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StoreWebController {

    @GetMapping("/store")
    public String store(){
        return "store";
    }

    @GetMapping("/store/new")
    public String newStore(){
        return "store-new";
    }

}

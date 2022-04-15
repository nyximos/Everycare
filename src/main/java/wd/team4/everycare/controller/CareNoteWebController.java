package wd.team4.everycare.controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NoArgsConstructor
public class CareNoteWebController {

    @GetMapping("/carenote")
    public String careNote(){
        return "carenote";
    }
}

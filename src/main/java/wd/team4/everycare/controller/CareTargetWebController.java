package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import wd.team4.everycare.service.FileStoreService;
import wd.team4.everycare.service.interfaces.CareTargetService;

@Controller
@RequiredArgsConstructor
public class CareTargetWebController {

    private final CareTargetService careTargetService;
    private final FileStoreService fileStoreService;

    @GetMapping("/carenote/caretargets/new")
    public String newCaretarget() {
        return "caretarget-new";
    }

}

package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetImage;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.service.FileStoreService;
import wd.team4.everycare.service.interfaces.CareTargetService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CareTargetWebController {

    private final CareTargetService careTargetService;
    private final CareTargetRepository careTargetRepository;

    @GetMapping("/carenote/caretargets/new")
    public String newCareTarget() {
        return "caretarget-new";
    }

    @GetMapping("/carenote/caretargets/{id}")
    public String careTargetDetail(@PathVariable Long id, Model model){
        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        if(careTarget.isEmpty()) return null;

        List<CareTargetImage> careTargetImages = careTargetService.findCareTargetImages(id);

        model.addAttribute("careTarget", careTarget.get());
        model.addAttribute("careTargetImages", careTargetImages);
        return "caretarget-view";
    }

}

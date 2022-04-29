package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetImage;
import wd.team4.everycare.dto.CareTargetScheduleDTO;
import wd.team4.everycare.dto.CareTargetViewDTO;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.service.CareTargetScheduleServiceImpl;
import wd.team4.everycare.service.CareTargetServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CareTargetWebController {

    private final CareTargetServiceImpl careTargetService;
    private final CareTargetRepository careTargetRepository;
    private final CareTargetScheduleServiceImpl careTargetScheduleService;

    @GetMapping("/dashboard/caretargets")
    public String caretargets(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String id = principalDetails.getUsername();
        List<CareTarget> careTargets = careTargetService.findCareTargets(id);
        if(careTargets!=null){
            model.addAttribute("careTargets", careTargets);
        }
        return "caretargets";
    }

    @GetMapping("/dashboard/caretargets/new")
    public String newCareTarget() {
        return "caretarget-new";
    }

    @GetMapping("/dashboard/caretargets/{id}")
    public String careTargetDetail(@PathVariable Long id, Model model){

        CareTargetViewDTO careTarget = careTargetService.findCareTarget(id);
        List<CareTargetImage> careTargetImages = careTargetService.findCareTargetImages(id);
        List<CareTargetScheduleDTO> schedules = careTargetScheduleService.findAllByCareTarget(id);

        model.addAttribute("careTarget", careTarget);
        model.addAttribute("careTargetImages", careTargetImages);
        model.addAttribute("schedules", schedules);
        return "caretarget-view";
    }

    @GetMapping("/dashboard/caretargets/{id}/update")
    public String updateCareTarget(@PathVariable Long id, Model model){
        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        if(careTarget.isEmpty()) return null;

        List<CareTargetImage> careTargetImages = careTargetService.findCareTargetImages(id);

        model.addAttribute("careTarget", careTarget.get());
        model.addAttribute("careTargetImages", careTargetImages);
        return "caretarget-update";
    }

}

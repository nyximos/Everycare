package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.dto.CareTargetScheduleDTO;
import wd.team4.everycare.dto.CareTargetViewDTO;
import wd.team4.everycare.service.CareTargetScheduleServiceImpl;
import wd.team4.everycare.service.CareTargetServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CareTargetScheduleWebController {

    private final CareTargetServiceImpl careTargetService;
    private final CareTargetScheduleServiceImpl careTargetScheduleService;

    @GetMapping("/dashboard/caretargets/{id}/schedule")
    public String newCareTargertSchedule(@PathVariable Long id, Model model){

        CareTargetViewDTO careTarget = careTargetService.findCareTarget(id);
        if(careTarget!=null){
            model.addAttribute("careTarget", careTarget);
        }

        return "caretarget-schedule-new";
    }

    @GetMapping("/dashboard/caretargets/{caretargetid}/schedules/{scheduleid}")
    public String careTargetSchedule(@PathVariable(value = "caretargetid") Long careTargetId,
                              @PathVariable(value = "scheduleid") Long scheduleId, Model model){

        CareTargetViewDTO careTarget = careTargetService.findCareTarget(careTargetId);

        List<CareTargetScheduleDTO> schedules = careTargetScheduleService.findAllByCareTarget(scheduleId);

        if(careTarget!=null) {
            model.addAttribute("careTarget", careTarget);
        }
        if(schedules!=null) {
            model.addAttribute("schedules", schedules);
        }
        return "caretarget-schedule-view";
    }
}

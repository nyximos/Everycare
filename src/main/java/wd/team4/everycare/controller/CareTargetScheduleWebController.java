package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleDTO;
import wd.team4.everycare.dto.CareTargetViewDTO;
import wd.team4.everycare.service.ActivityInformationServiceImpl;
import wd.team4.everycare.service.CareTargetScheduleServiceImpl;
import wd.team4.everycare.service.CareTargetServiceImpl;
import wd.team4.everycare.service.interfaces.ActivityInformationService;

@Controller
@RequiredArgsConstructor
public class CareTargetScheduleWebController {

    private final CareTargetServiceImpl careTargetService;
    private final CareTargetScheduleServiceImpl careTargetScheduleService;
    private final ActivityInformationServiceImpl activityInformationService;

    @GetMapping("/dashboard/caretargets/{id}/schedule")
    public String newCareTargertSchedule(@PathVariable Long id, Model model){

        CareTargetViewDTO careTarget = careTargetService.webFindCareTarget(id);
        if(careTarget!=null){
            model.addAttribute("careTarget", careTarget);
        }

        return "caretarget-schedule-new";
    }

    @GetMapping("/dashboard/caretargets/{caretargetid}/schedules/{scheduleid}")
    public String careTargetSchedule(@PathVariable(value = "caretargetid") Long careTargetId,
                              @PathVariable(value = "scheduleid") Long scheduleId, Model model){

        CareTargetViewDTO careTarget = careTargetService.webFindCareTarget(careTargetId);

        CareTargetScheduleDTO schedule = careTargetScheduleService.webFindById(scheduleId);

        if(careTarget!=null && schedule!=null) {
            model.addAttribute("careTarget", careTarget);
            model.addAttribute("schedule", schedule);
        }
        return "caretarget-schedule-view";
    }

    @GetMapping("/dashboard/caretargets/{caretargetid}/schedules/{scheduleid}/activities/new")
    public String newActivities(@PathVariable(value = "caretargetid") Long careTargetId,
                                @PathVariable(value = "scheduleid") Long scheduleId, Model model){

        CareTargetViewDTO careTarget = careTargetService.webFindCareTarget(careTargetId);
        CareTargetScheduleDTO schedule = careTargetScheduleService.webFindById(scheduleId);
        activityInformationService.webFindAllBySchedule(scheduleId);


        if(careTarget!=null && schedule!=null) {
            model.addAttribute("careTarget", careTarget);
            model.addAttribute("schedule", schedule);
        }
        return "caretarget-schedule-activity";
    }

}

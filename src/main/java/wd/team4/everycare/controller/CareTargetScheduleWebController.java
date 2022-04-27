package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CareTargetScheduleWebController {

    @GetMapping("/carenote/caretargets/{id})/schedules/new")
    public String newCareTargertSchedule(@PathVariable Long id){
        return "caretarget-schedule-new";
    }

    @GetMapping("/carenote/caretargets/{caretargetid}/schedules/{scheduleid}")
    public String careTargetSchedule(@PathVariable(value = "caretargetid") Long careTargetId,
                              @PathVariable(value = "scheduleid") Long scheduleId){

        return "caretarget-schedule-view";
    }
}

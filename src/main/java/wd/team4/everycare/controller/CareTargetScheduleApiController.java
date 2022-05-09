package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.CareTargetScheduleServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareTargetScheduleApiController {

    private final CareTargetScheduleServiceImpl careTagetScheduleService;

    @PostMapping("/dashboard/caretargets/{id}/schedules")
    public ResponseEntity<MyResponse> saveSchedule(@ModelAttribute CareTargetScheduleDTO careTargetScheduleDTO,
                                                   @PathVariable("id") Long id){
        System.out.println("id = " + id);
        ResponseEntity<MyResponse> responseEntity = careTagetScheduleService.save(careTargetScheduleDTO);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/caretargets/{caretargetid}/schedules/{scheduleid}")
    public ResponseEntity<MyResponse> deleteSchedule(@PathVariable(value = "caretargetid") Long careTargetId,
                                                     @PathVariable(value = "scheduleid") Long scheduleId){
        ResponseEntity<MyResponse> responseEntity = careTagetScheduleService.deleteById(scheduleId);
        return responseEntity;
    }
}
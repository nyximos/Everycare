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

    private final CareTargetScheduleServiceImpl careTargetScheduleService;

    @GetMapping("/dashboard/caretargets/{id}/schedules")
    public ResponseEntity<MyResponse> getAll(@PathVariable("id") Long id){
        careTargetScheduleService.getAll(id);
    }

    @PostMapping("/dashboard/caretargets/{id}/schedules")
    public ResponseEntity<MyResponse> saveSchedule(@ModelAttribute CareTargetScheduleDTO careTargetScheduleDTO,
                                                   @PathVariable("id") Long id){
        ResponseEntity<MyResponse> responseEntity = careTargetScheduleService.save(careTargetScheduleDTO);
        return responseEntity;
    }

    @PatchMapping("/dashboard/caretargets/{caretargetid}/schedules/{scheduleid}")
    public ResponseEntity<MyResponse> updateSchedule(@ModelAttribute CareTargetScheduleDTO careTargetScheduleDTO,
                                                     @PathVariable("caretargetid") Long careTargetId,
                                                     @PathVariable(value = "scheduleid") Long scheduleId){
        ResponseEntity<MyResponse> responseEntity = careTargetScheduleService.update(careTargetScheduleDTO);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/caretargets/{caretargetid}/schedules/{scheduleid}")
    public ResponseEntity<MyResponse> deleteSchedule(@PathVariable(value = "caretargetid") Long careTargetId,
                                                     @PathVariable(value = "scheduleid") Long scheduleId){
        ResponseEntity<MyResponse> responseEntity = careTargetScheduleService.delete(scheduleId);
        return responseEntity;
    }
}
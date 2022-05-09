package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.ActivityInformationServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActivityInformationApiController {

    private final ActivityInformationServiceImpl activityInformationService;

    @PostMapping("/dashboard/caretargets/{careTargetId}/schedules/{scheduleId}/activities")
    public ResponseEntity<MyResponse> saveActivityInformation(
            @ModelAttribute ActivityInformationFormDTO activityInformationFormDTO){

        ResponseEntity<MyResponse> responseEntity = activityInformationService.save(activityInformationFormDTO);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/caretargets/{careTargetId}/schedules/{scheduleId}/activities/{activityId}")
    public ResponseEntity<MyResponse> removeActivityInformation (@PathVariable("activityId") Long activityId){
        ResponseEntity<MyResponse> responseEntity = activityInformationService.remove(activityId);
        return responseEntity;
    }
}

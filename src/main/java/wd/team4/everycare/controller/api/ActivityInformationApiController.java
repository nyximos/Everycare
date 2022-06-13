package wd.team4.everycare.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.ActivityInformationServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActivityInformationApiController {

    private final ActivityInformationServiceImpl activityInformationService;

    @GetMapping("/dashboard/caretargets/{careTargetId}/schedules/{scheduleId}/activities/{activityId}")
    public ResponseEntity<MyResponse> getActivityInformation(@PathVariable("activityId") Long id){
        ResponseEntity<MyResponse> responseEntity = activityInformationService.get(id);
        return responseEntity;
    }

    @PostMapping("/dashboard/caretargets/{careTargetId}/schedules/{scheduleId}/activities")
    public ResponseEntity<MyResponse> saveActivityInformation(
            @ModelAttribute ActivityInformationFormDTO activityInformationFormDTO,
            @PathVariable("scheduleId") Long id) {

        ResponseEntity<MyResponse> responseEntity = activityInformationService.save(id, activityInformationFormDTO);
        return responseEntity;
    }

    @PatchMapping("/dashboard/caretargets/{careTargetId}/schedules/{scheduleId}/activities/{activityId}")
    public ResponseEntity<MyResponse> updateActivityInformation(
            @PathVariable("activityId") Long id,
            @ModelAttribute ActivityInformationFormDTO activityInformationFormDTO){
        ResponseEntity<MyResponse> responseEntity = activityInformationService.update(id,activityInformationFormDTO);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/caretargets/{careTargetId}/schedules/{scheduleId}/activities/{activityId}")
    public ResponseEntity<MyResponse> removeActivityInformation(@PathVariable("activityId") Long activityId){
        ResponseEntity<MyResponse> responseEntity = activityInformationService.remove(activityId);
        return responseEntity;
    }
}

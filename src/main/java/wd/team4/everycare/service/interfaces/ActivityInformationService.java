package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationFormDTO;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationViewDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.util.List;

public interface ActivityInformationService {

    List<ActivityInformationViewDTO> webFindAllByScheduleId(Long scheduleId);

    ResponseEntity<MyResponse> save(ActivityInformationFormDTO activityInformationFormDTO);

    ResponseEntity<MyResponse> remove(Long activityId);
}

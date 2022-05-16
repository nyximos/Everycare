package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationFormDTO;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationViewDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.util.List;

public interface ActivityInformationService {

    ResponseEntity<MyResponse> get(Long id);

    ResponseEntity<MyResponse> save(Long id, ActivityInformationFormDTO activityInformationFormDTO);

    ResponseEntity<MyResponse> update(Long id, ActivityInformationFormDTO activityInformationFormDTO);

    ResponseEntity<MyResponse> remove(Long activityId);

    List<ActivityInformationViewDTO> webFindAllByScheduleId(Long scheduleId);

}

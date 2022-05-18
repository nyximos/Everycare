package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.dto.careTargetSchedule.ActivityClassificationDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.ActivityClassificationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityClassificationServiceImpl {

    ActivityClassificationRepository activityClassificationRepository;


    public ResponseEntity<MyResponse> getAll() {

        List<ActivityClassification> activityClassifications = activityClassificationRepository.findAll();

        List<ActivityClassificationDTO> activityClassificationDTOs = new ArrayList<>();

        for (ActivityClassification activityClassification : activityClassifications) {
            ActivityClassificationDTO dto = ActivityClassificationDTO.builder()
                    .id(activityClassification.getId())
                    .name(activityClassification.getName())
                    .level(activityClassification.getLevel())
                    .ParentId(activityClassification.getActivityClassification().getId())
                    .build();
            activityClassificationDTOs.add(dto);
        }

        MyResponse<List<ActivityClassificationDTO>> body = MyResponse.<List<ActivityClassificationDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(activityClassificationDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}

package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.domain.ActivityInformation;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationFormDTO;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationViewDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.ActivityClassificationRepository;
import wd.team4.everycare.repository.ActivityInformationRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.service.interfaces.ActivityInformationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityInformationServiceImpl implements ActivityInformationService {

    private final ActivityInformationRepository activityInformationRepository;
    private final ActivityClassificationRepository activityClassificationRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;

    @Override
    public List<ActivityInformationViewDTO> webFindAllByScheduleId(Long id) {

        List<ActivityInformation> activityInformations = activityInformationRepository.findAllCareTargetSchedule(id);
        if (activityInformations.isEmpty()) {
            return null;
        }
        List<ActivityInformationViewDTO> activityInformationListViewDTOs = new ArrayList<>();

        activityInformations.stream().map(activityInformation -> activityInformation.toListViewDTO()).forEach(activityInformationListViewDTOs::add);

        return activityInformationListViewDTOs;
    }

    @Override
    public ResponseEntity<MyResponse> save(ActivityInformationFormDTO activityInformationFormDTO) {

        Long activityClassificationId = activityInformationFormDTO.getActivityClassificationId();
        Optional<ActivityClassification> activityClassification = activityClassificationRepository.findById(activityClassificationId);
        ActivityClassification activityClassificationEntity = activityClassification.orElse(null);

        Long scheduleId = activityInformationFormDTO.getScheduleId();
        Optional<CareTargetSchedule> careTargetSchedule = careTargetScheduleRepository.findById(scheduleId);
        CareTargetSchedule careTargetScheduleEntity = careTargetSchedule.orElse(null);

        ActivityInformation activityInformation = ActivityInformation.builder()
                .startTime(activityInformationFormDTO.getStartTime())
                .endTime(activityInformationFormDTO.getEndTime())
                .requirement(activityInformationFormDTO.getRequirement())
                .build();

        activityInformationRepository.save(activityInformation);
        activityInformation.save(activityClassificationEntity, careTargetScheduleEntity);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> remove(Long id) {
        activityInformationRepository.deleteById(id);
        
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }
}

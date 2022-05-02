package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ActivityInformation;
import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationListViewDTO;
import wd.team4.everycare.repository.ActivityInformationRepository;
import wd.team4.everycare.service.interfaces.ActivityInformationService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityInformationServiceImpl implements ActivityInformationService {

    private final ActivityInformationRepository activityInformationRepository;

    @Override
    public List<ActivityInformationListViewDTO> webFindAllBySchedule(Long id) {

        List<ActivityInformation> activityInformations = activityInformationRepository.findAllByCareTargetScheduleId(id);
        List<ActivityInformationListViewDTO> activityInformationListViewDTOs = new ArrayList<>();

        if(activityInformationListViewDTOs.isEmpty()){
            return null;
        }

        activityInformations.stream().map(activityInformation -> activityInformation.toListViewDTO()).forEach(activityInformationListViewDTOs::add);

        return activityInformationListViewDTOs;
    }
}

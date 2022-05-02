package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.dto.careTargetSchedule.ActivityInformationListViewDTO;

import java.util.List;

public interface ActivityInformationService {

    List<ActivityInformationListViewDTO> webFindAllBySchedule(Long scheduleId);
}

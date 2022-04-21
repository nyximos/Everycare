package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.dto.CareTargetScheduleDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.service.interfaces.CareTargetScheduleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CareTargetScheduleServiceImpl implements CareTargetScheduleService {

    private final CareTargetRepository careTargetRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;

    @Override
    public List<CareTargetScheduleDTO> findAllByCareTarget(Long id) {
        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        CareTarget careTargetEntity = careTarget.orElse(null);
        List<CareTargetSchedule> careTargetSchedules = careTargetScheduleRepository.findByCareTargetId(careTargetEntity.getId());
        List<CareTargetScheduleDTO> careTargetScheduleDTOs = new ArrayList<>();

        if(careTargetSchedules.isEmpty()) {
            return null;
        }

        careTargetSchedules.stream().map(careTargetSchedule -> careTargetSchedule.toDTO()).forEach(careTargetScheduleDTOs::add);

        return careTargetScheduleDTOs;
    }
}

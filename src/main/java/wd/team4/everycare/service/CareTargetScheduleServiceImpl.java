package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleDTO;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.dto.product.ProductListViewDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.ActivityInformationRepository;
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
    private final ActivityInformationRepository activityInformationRepository;

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

    @Override
    public ResponseEntity<MyResponse> getAll(Long id) {
        List<CareTargetSchedule> careTargetSchedules = careTargetScheduleRepository.findAll();
        List<CareTargetScheduleListDTO> careTargetScheduleListDTOs = new ArrayList<>();

        if(careTargetSchedules.isEmpty()){
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("등록된 스케줄이 없습니다.")
                    .build();

            return  new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        for (CareTargetSchedule careTargetSchedule : careTargetSchedules) {
            CareTargetScheduleListDTO careTargetScheduleListDTO = CareTargetScheduleListDTO.builder()
                    .id(careTargetSchedule.getId())
                    .name(careTargetSchedule.getName())
                    .startTime(careTargetSchedule.getStartTime())
                    .endTime(careTargetSchedule.getEndTime())
                    .build();
            careTargetScheduleListDTOs.add(careTargetScheduleListDTO);
        }

        MyResponse<List<CareTargetScheduleListDTO>> body = MyResponse.<List<CareTargetScheduleListDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careTargetScheduleListDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> save(CareTargetScheduleDTO careTargetScheduleDTO) {

        Long careTargetId = careTargetScheduleDTO.getCareTarget();
        Optional<CareTarget> careTargetEntity = careTargetRepository.findById(careTargetId);
        CareTarget careTarget = careTargetEntity.orElse(null);

        CareTargetSchedule careTargetSchedule = CareTargetSchedule.builder()
                .name(careTargetScheduleDTO.getName())
                .startTime(careTargetScheduleDTO.getStartTime())
                .endTime(careTargetScheduleDTO.getEndTime())
                .build();

        if(careTarget != null){
            careTargetSchedule.saveCareTarget(careTarget);
            careTargetScheduleRepository.save(careTargetSchedule);
        }

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return  new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public CareTargetScheduleDTO webFindById(Long id) {

        Optional<CareTargetSchedule> careTargetSchedule = careTargetScheduleRepository.findById(id);
        CareTargetSchedule careTargetScheduleEntity = careTargetSchedule.orElse(null);
        CareTargetScheduleDTO careTargetScheduleDTO = careTargetScheduleEntity.toDTO();

        return careTargetScheduleDTO;
    }

    @Override
    public ResponseEntity<MyResponse> delete(Long scheduleId) {

        Optional<CareTargetSchedule> careTargetSchedule = careTargetScheduleRepository.findById(scheduleId);
        CareTargetSchedule careTargetScheduleEntity = careTargetSchedule.orElse(null);

        activityInformationRepository.deleteAllByCareTargetSchedule(careTargetScheduleEntity);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return  new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }

    @Override
    public ResponseEntity<MyResponse> update(CareTargetScheduleDTO careTargetScheduleDTO) {

        Optional<CareTargetSchedule> careTargetSchedule = careTargetScheduleRepository.findById(careTargetScheduleDTO.getId());
        CareTargetSchedule careTargetScheduleEntity = careTargetSchedule.orElse(null);
        if(careTargetScheduleEntity!=null) {
            careTargetScheduleEntity.update(careTargetScheduleDTO);
        }

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}

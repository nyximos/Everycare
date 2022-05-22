package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.HealthClassification;
import wd.team4.everycare.domain.HealthRecord;
import wd.team4.everycare.dto.careRecord.HealthRecordDTO;
import wd.team4.everycare.dto.careRecord.HealthRecordFormDTO;
import wd.team4.everycare.dto.product.MemberProductListViewDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareNoteRepository;
import wd.team4.everycare.repository.HealthClassificationRepository;
import wd.team4.everycare.repository.HealthRecordRepository;
import wd.team4.everycare.service.interfaces.HealthRecordService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HealthRecordServiceImpl implements HealthRecordService {

    private final CareNoteRepository careNoteRepository;
    private final HealthClassificationRepository healthClassificationRepository;
    private final HealthRecordRepository healthRecordRepository;

    @Override
    public ResponseEntity<MyResponse> getAll(Long id) {

        Optional<CareNote> careNote = careNoteRepository.findById(id);
        CareNote careNoteEntity = careNote.orElse(null);

        List<HealthRecord> healthRecords = healthRecordRepository.findByCareNote(careNoteEntity);
        List<HealthRecordDTO> healthRecordDTOs = new ArrayList<>();

        if(healthRecords.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("작성된 기록이 없습니다.")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        for (HealthRecord healthRecord : healthRecords) {
            HealthRecordDTO dto = HealthRecordDTO.builder()
                    .id(healthRecord.getId())
                    .healthStatus(healthRecord.getHealthStatus())
                    .updatedAt(healthRecord.getUpdatedAt())
                    .healthClassification(String.valueOf(healthRecord.getHealthClassification()))
                    .build();

            healthRecordDTOs.add(dto);
        }

        MyResponse<List<HealthRecordDTO>> body = MyResponse.<List<HealthRecordDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(healthRecordDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> get(Long id) {

        Optional<HealthRecord> healthRecord = healthRecordRepository.findById(id);
        HealthRecord healthRecordEntity = healthRecord.orElse(null);

        HealthRecordDTO healthRecordDTO = HealthRecordDTO.builder()
                .id(healthRecordEntity.getId())
                .healthStatus(healthRecordEntity.getHealthStatus())
                .updatedAt(healthRecordEntity.getUpdatedAt())
                .healthClassification(String.valueOf(healthRecordEntity.getHealthClassification()))
                .build();

        MyResponse<HealthRecordDTO> body = MyResponse.<HealthRecordDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(healthRecordDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> save(Long id, HealthRecordFormDTO healthRecordFormDTO) {

        Optional<CareNote> careNote = careNoteRepository.findById(id);
        CareNote careNoteEntity = careNote.orElse(null);

        Optional<HealthClassification> healthClassification = healthClassificationRepository.findById(healthRecordFormDTO.getHealthClassificationId());
        HealthClassification healthClassificationEntity = healthClassification.orElse(null);

        HealthRecord healthRecord = HealthRecord.builder()
                .healthStatus(healthRecordFormDTO.getHealthStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .healthClassification(healthClassificationEntity)
                .careTarget(careNoteEntity.getContract().getJobOffer().getCareTarget())
                .careNote(careNoteEntity)
                .build();

        if (healthRecord.getDetailComment() != null) {
            healthRecord.saveDetailComment(healthRecordFormDTO.getDetailComment());
        }

        healthRecordRepository.save(healthRecord);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> update(Long id, HealthRecordFormDTO healthRecordFormDTO) {

        Optional<HealthRecord> healthRecord = healthRecordRepository.findById(id);
        HealthRecord healthRecordEntity = healthRecord.orElse(null);
        Long healthClassificationId = healthRecordFormDTO.getHealthClassificationId();
        Optional<HealthClassification> healthClassification = healthClassificationRepository.findById(healthClassificationId);
        HealthClassification healthClassificationEntity = healthClassification.orElse(null);
        healthRecordEntity.saveCategory(healthClassificationEntity);
        healthRecordEntity.update(healthRecordFormDTO);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);    }

    @Override
    public ResponseEntity<MyResponse> remove(Long id) {

        healthRecordRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}

package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareNote;
import wd.team4.everycare.domain.HealthClassification;
import wd.team4.everycare.domain.HealthRecord;
import wd.team4.everycare.dto.careRecord.HealthRecordFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareNoteRepository;
import wd.team4.everycare.repository.HealthClassificationRepository;
import wd.team4.everycare.repository.HealthRecordRepository;
import wd.team4.everycare.service.interfaces.HealthRecordService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HealthRecordServiceImpl implements HealthRecordService {

    private final CareNoteRepository careNoteRepository;
    private final HealthClassificationRepository healthClassificationRepository;
    private final HealthRecordRepository healthRecordRepository;

    @Override
    public ResponseEntity<MyResponse> save(Long id, HealthRecordFormDTO healthRecordFormDTO) {

        Optional<CareNote> careNote = careNoteRepository.findById(id);
        CareNote careNoteEntity = careNote.orElse(null);

        Optional<HealthClassification> healthClassification = healthClassificationRepository.findById(healthRecordFormDTO.getHealthClassificationId());
        HealthClassification healthClassificationEntity = healthClassification.orElse(null);

        HealthRecord healthRecord = HealthRecord.builder()
                .healthStatus(healthRecordFormDTO.getHealthStatus())
                .detailComment(healthRecordFormDTO.getDetailComment())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .healthClassification(healthClassificationEntity)
                .careTarget(careNoteEntity.getContract().getJobOffer().getCareTarget())
                .careNote(careNoteEntity)
                .build();

        healthRecordRepository.save(healthRecord);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}

package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.HealthClassification;
import wd.team4.everycare.dto.HealthClassificationDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.HealthClassificationRepository;
import wd.team4.everycare.service.interfaces.HealthClassificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HealthClassificationServiceImpl implements HealthClassificationService {

    private final HealthClassificationRepository healthClassificationRepository;

    @Override
    public ResponseEntity<MyResponse> getAll() {

        List<HealthClassification> healthClassifications = healthClassificationRepository.findAll();
        List<HealthClassificationDTO> healthClassificationDTOs = new ArrayList<>();

        for (HealthClassification healthClassification : healthClassifications) {
            HealthClassificationDTO healthClassificationDTO = HealthClassificationDTO.builder()
                    .id(healthClassification.getId())
                    .name(healthClassification.getName())
                    .build();

            healthClassificationDTOs.add(healthClassificationDTO);
        }

        MyResponse<List<HealthClassificationDTO>> body = MyResponse.<List<HealthClassificationDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(healthClassificationDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> get(Long id) {

        Optional<HealthClassification> healthClassification = healthClassificationRepository.findById(id);
        HealthClassification healthClassificationEntity = healthClassification.orElse(null);

        HealthClassificationDTO healthClassificationDTO = HealthClassificationDTO.builder()
                .id(healthClassificationEntity.getId())
                .name(healthClassificationEntity.getName())
                .build();

        MyResponse<HealthClassificationDTO> body = MyResponse.<HealthClassificationDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(healthClassificationDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> save(String name) {

        HealthClassification healthClassification = HealthClassification.builder()
                .name(name)
                .build();

        healthClassificationRepository.save(healthClassification);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> update(Long id, String name) {

        Optional<HealthClassification> healthClassification = healthClassificationRepository.findById(id);
        HealthClassification healthClassificationEntity = healthClassification.orElse(null);
        healthClassificationEntity.updateName(name);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> remove(Long id) {

        healthClassificationRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);    }

}
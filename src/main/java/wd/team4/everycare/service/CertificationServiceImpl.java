package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Certification;
import wd.team4.everycare.domain.CertificationClassification;
import wd.team4.everycare.dto.CertificationFormDTO;
import wd.team4.everycare.dto.CertificationViewDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.CertificationClassificationRepository;
import wd.team4.everycare.repository.CertificationRepository;
import wd.team4.everycare.service.interfaces.CertificationService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;
    private final CareSitterRepository careSitterRepository;
    private final CertificationClassificationRepository certificationClassificationRepository;
    private final FileStoreService fileStoreService;

    @Override
    public List<CertificationViewDTO> findAllByCareSitter(Long id) {

//        Optional<CareSitter> careSitter = careSitterRepository.findById(id);
//        CareSitter careSitterEntity = careSitter.orElse(null);
        List<Certification> certifications = certificationRepository.findAllByCareSitterId(id);
        List<CertificationViewDTO> certificationViewDTOs = new ArrayList<>();

        if(certifications.isEmpty()){
            return null;
        }

        certifications.stream().map(certification -> certification.toViewDTO()).forEach(certificationViewDTOs::add);

        return certificationViewDTOs;
    }

    @Override
    public ResponseEntity<MyResponse> save(CertificationFormDTO certificationFormDTO) throws IOException {
        LocalDateTime time = LocalDateTime.now();

        String classificationName = certificationFormDTO.getClassification();
        CertificationClassification certificationClassification = certificationClassificationRepository.findByName(classificationName);

        Optional<CareSitter> careSitter = careSitterRepository.findById(certificationFormDTO.getCareSitterId());
        CareSitter careSitterEntity = careSitter.orElse(null);

        UploadFile attachFile = fileStoreService.storeFile(certificationFormDTO.getAttachFile());

        Certification certification = Certification.builder()
                .name(classificationName)
                .uploadFileName(attachFile.getUploadFileName())
                .storeFileName(attachFile.getStoreFileName())
                .adminApproval(0)
                .createdAt(time)
                .careSitter(careSitterEntity)
                .certificationClassification(certificationClassification)
                .build();

        certificationRepository.save(certification);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public List<CertificationViewDTO> webFindAllThatRequireApproval() {
        List<Certification> certifications = certificationRepository.findAllByAdminApproval(0);
        List<CertificationViewDTO> certificationViewDTOs = new ArrayList<>();

        if(certifications.isEmpty()){
            return null;
        }

        certifications.stream().map(certification -> certification.toViewDTO()).forEach(certificationViewDTOs::add);

        return certificationViewDTOs;
    }

    @Override
    public List<CareSitter> webFindAllByCareSitter() {
        List<Certification> certifications = certificationRepository.findAll();
        List<CareSitter> careSitters = new ArrayList<>();
        for (Certification certification : certifications) {
            CareSitter careSitter = certification.getCareSitter();
            for (CareSitter sitter : careSitters) {
                if(careSitter == sitter){
                    break;
                }
                careSitters.add(sitter);
            }
        }
        return careSitters;
    }
}
package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Certification;
import wd.team4.everycare.domain.CertificationClassification;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.CertificationFormDTO;
import wd.team4.everycare.dto.CertificationViewDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.certification.CertificationDetailDTO;
import wd.team4.everycare.dto.certification.CertificationListDTO;
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
    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {

        CareSitter careSitter = principalDetails.getCareSitter();

        List<Certification> certifications = certificationRepository.findAllByCareSitter(careSitter);

        List<CertificationListDTO> certificationAdminListDTOs = new ArrayList<>();

        for (Certification certification : certifications) {
            CertificationListDTO certificationAdminListDTO = CertificationListDTO.builder()
                    .id(certification.getId())
                    .classificationName(certification.getName())
                    .memberId(careSitter.getMember().getId())
                    .memberName(careSitter.getName())
                    .build();

            certificationAdminListDTOs.add(certificationAdminListDTO);
        }

        if(certificationAdminListDTOs.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("등록한 자격증이 없습니다.")
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        } else {
            MyResponse<List<CertificationListDTO>> body = MyResponse.<List<CertificationListDTO>>builder()
                    .header(StatusEnum.OK)
                    .message("성공")
                    .body(certificationAdminListDTOs)
                    .build();

            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> get(@AuthenticationPrincipal PrincipalDetails principalDetails, Long id) {

        Member user = principalDetails.getUser();

        Optional<Certification> certification = certificationRepository.findById(id);
        Certification certificationEntity = certification.orElse(null);

        CertificationDetailDTO certificationDetailDTO = CertificationDetailDTO.builder()
                .id(certificationEntity.getId())
                .name(certificationEntity.getName())
                .uploadName(certificationEntity.getUploadFileName())
                .storeName(certificationEntity.getStoreFileName())
                .createdAt(certificationEntity.getCreatedAt())
                .memberId(user.getId())
                .memberName(user.getName())
                .build();

        MyResponse<CertificationDetailDTO> body = MyResponse.<CertificationDetailDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(certificationDetailDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
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
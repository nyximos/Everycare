package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.dto.CareSitterFormDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.repository.CareSitterImageRepository;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.interfaces.CareSitterService;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CareSitterServiceImpl implements CareSitterService {

    private final FileStoreService fileStoreService;
    private final CareSitterRepository careSitterRepository;
    private final CareSitterImageRepository careSitterImageRepository;
    private final MemberRepository memberRepository;

    // DB 조회일 경우 @Transactional(readOnly = true) 사용

    @Override
    public Long save(CareSitterFormDTO careSitterFormDTO) throws IOException {
        CareSitter careSitter = careSitterDtoToCareSitter(careSitterFormDTO);
        careSitterRepository.save(careSitter);

        if(careSitterFormDTO.getAttachFile()!=null) {
            UploadFile attachFile = fileStoreService.storeFile(careSitterFormDTO.getAttachFile());
            CareSitterImage careSitterImage = careSitterDtoToImage(careSitter, attachFile);
            careSitterImageRepository.save(careSitterImage);
        }
        if(careSitterFormDTO.getAttachFiles()!=null) {
            List<UploadFile> attachFiles = fileStoreService.storeFiles(careSitterFormDTO.getAttachFiles());
            for (UploadFile file : attachFiles) {
                CareSitterImage careSitterImage2 = careSitterDtoToImage(careSitter, file);
                careSitterImageRepository.save(careSitterImage2);
            }
        }



        return careSitter.getId();
    }

    @Override
    public CareSitterImage careSitterDtoToImage(CareSitter careSitter, UploadFile attachFile) throws IOException {
        return CareSitterImage.builder()
                .uploadFileName(attachFile.getUploadFileName())
                .storeFileName(attachFile.getStoreFileName())
                .careSitter(careSitter)
                .build();
    }

    @Override
    public CareSitter careSitterDtoToCareSitter(CareSitterFormDTO dto) {
        return CareSitter.builder()
                .preferredType(dto.getPreferredType())
                .desiredDayWeek(dto.getDesiredHourlyWage())
                .activityTime(dto.getActivityTime())
                .desiredHourlyWage(dto.getDesiredHourlyWage())
                .desiredMonthlyWage(dto.getDesiredMonthlyWage())
                .cctvAgreement(dto.getCctvAgreement())
                .vaccination(dto.getVaccination())
                .introduction(dto.getIntroduction())
                .disclosureStatus(1)
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .member(dto.getMember())
                .build();
    }

    @Override
    public void removeCareSitter(Long id) {
        careSitterRepository.deleteById(id);
    }

    @Override
    public CareSitter isPresent(Long id) {
        Optional<CareSitter> careSitter = careSitterRepository.findById(id);
        return careSitter.get();
    }

    @Override
    public boolean isEmpty(String id) {
        if(isEmpty(String.valueOf(memberRepository.findById(id)))) {
            return true;
        }
        return false;
    }

    @Override
    public List<CareSitterImage> findCareSitterImages(Long id) {
        CareSitter careSitter = isPresent(id);
        return careSitter.getCareSitterImages();

    }


}

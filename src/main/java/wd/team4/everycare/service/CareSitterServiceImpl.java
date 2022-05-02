package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.careSitter.CareSitterFormDTO;
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
        CareSitter careSitter = careSitterFormDTO.toCareSitter();
        careSitterRepository.save(careSitter);

        List<UploadFile> attachFiles = fileStoreService.storeFiles(careSitterFormDTO.getAttachFiles());

        for (UploadFile file : attachFiles) {
            CareSitterImage careSitterImage = careSitterDtoToImage(careSitter, file);
            careSitterImageRepository.save(careSitterImage);
        }

        return careSitter.getId();
    }

    @Override
    public CareSitter isPresent(Long id) {
        Optional<CareSitter> careSitter = careSitterRepository.findById(id);
        return careSitter.get();
    }

    @Override
    public boolean isEmpty(String id) {
        if (isEmpty(String.valueOf(memberRepository.findById(id)))) {
            return true;
        }
        return false;
    }

    @Override
    public CareSitter findCareSitter(String id) {
        Optional<Member> member = memberRepository.findById(id);
        CareSitter careSitter = member.get().getCareSitter();
        return careSitter;
    }

    @Override
    public List<CareSitterImage> findCareSitterImages(Long id) {
        CareSitter careSitter = isPresent(id);
        return careSitter.getCareSitterImages();
    }

    @Override
    public String update(Long id, CareSitterFormDTO careSitterFormDTO) {
        Optional<CareSitter> careSitter = careSitterRepository.findById(id);
        CareSitter careSitterEntity = careSitter.orElse(null);
        if (careSitterEntity == null) {
            System.out.println("케어시터가 없으므로 종료해야함");
            return "실패했씀다~";
        }
        careSitterEntity.updateInfo(careSitterFormDTO);
        return "수정했슴다~";
    }

    @Override
    public CareSitterImage careSitterDtoToImage(CareSitter careSitter, UploadFile attachFile) throws IOException {
        return CareSitterImage.builder()
                .uploadFileName(attachFile.getUploadFileName())
                .storeFileName(attachFile.getStoreFileName())
                .careSitter(careSitter)
                .build();
    }
}

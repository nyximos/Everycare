package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.ImageDTO;
import wd.team4.everycare.dto.MultipartFileDTO;
import wd.team4.everycare.dto.careSitter.CareSitterDTO;
import wd.team4.everycare.dto.careSitter.CareSitterFormDTO;
import wd.team4.everycare.dto.UploadFile;
import wd.team4.everycare.dto.careSitter.CareSitterListDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobSearchDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareSitterImageRepository;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.repository.query.CareSitterQueryRepository;
import wd.team4.everycare.service.interfaces.CareSitterService;

import java.io.IOException;

import java.util.ArrayList;
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
    private final CareSitterQueryRepository careSitterQueryRepository;

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

    @Override
    public ResponseEntity<MyResponse> findCareSitterByMember(PrincipalDetails principalDetails) {
        Member member = principalDetails.getUser();
        CareSitter careSitter = careSitterRepository.findByMember(member);

        List<CareSitterImage> careSitterImages = careSitterImageRepository.findAllByCareSitter(careSitter);

        List<ImageDTO> careSitterImageDTOs = new ArrayList<>();
        for (CareSitterImage careSitterImage : careSitterImages) {
            ImageDTO imageDTO = ImageDTO.builder()
                    .id(careSitterImage.getId())
                    .uploadFileName(careSitterImage.getUploadFileName())
                    .storeFileName(careSitterImage.getStoreFileName())
                    .build();
            careSitterImageDTOs.add(imageDTO);
        }

        CareSitterDTO careSitterDTO = CareSitterDTO.builder()
                .id(careSitter.getId())
                .name(careSitter.getName())
                .preferredType(careSitter.getPreferredType())
                .hopefulRegion(careSitter.getHopefulRegion())
                .desiredDayWeek(careSitter.getDesiredDayWeek())
                .activityTime(careSitter.getActivityTime())
                .desiredHourlyWage(careSitter.getDesiredHourlyWage())
                .desiredMonthlyWage(careSitter.getDesiredMonthlyWage())
                .cctvAgreement(careSitter.getCctvAgreement())
                .vaccination(careSitter.getVaccination())
                .introduction(careSitter.getIntroduction())
                .disclosureStatus(careSitter.getDisclosureStatus())
                .createdAt(careSitter.getCreatedAt())
                .updatedAt(careSitter.getUpdatedAt())
                .imageDTOs(careSitterImageDTOs)
                .build();

        MyResponse<CareSitterDTO> body = MyResponse.<CareSitterDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careSitterDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);


    }

    @Override
    public ResponseEntity<MyResponse> saveImage(Long id, MultipartFileDTO imageDTO) throws IOException {

        UploadFile attatchFile = fileStoreService.storeFile(imageDTO.getAttachFile());

        Optional<CareSitter> careSitter = careSitterRepository.findById(id);
        CareSitter careSitterEntity = careSitter.orElse(null);

        CareSitterImage careSitterImage = CareSitterImage.builder()
                .uploadFileName(attatchFile.getUploadFileName())
                .storeFileName(attatchFile.getStoreFileName())
                .careSitter(careSitterEntity)
                .build();

        careSitterImageRepository.save(careSitterImage);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> removeImage(Long id) {

        careSitterImageRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getAllByRegion(String region) {

        List<CareSitter> careSitters = careSitterQueryRepository.findAllByRegion(region);

        if(careSitters.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("해당 지역의 케어시터가 없습니다.")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        List<CareSitterListDTO> careSitterDTOList = new ArrayList<>();


        for (CareSitter careSitter : careSitters) {
            Long id = careSitter.getId();
            Member member = careSitter.getMember();

            List<String> storeFileNames = new ArrayList<>();
            List<CareSitterImage> careSitterImage = careSitterImageRepository.findByCareSitterId(id);
            for (CareSitterImage sitterImage : careSitterImage) {
                String storeFileName = sitterImage.getStoreFileName();
                storeFileNames.add(storeFileName);
            }

            CareSitterListDTO dto = CareSitterListDTO.builder()
                    .id(careSitter.getId())
                    .cctvAgreement(careSitter.getCctvAgreement())
                    .isVaccinated(careSitter.getVaccination())
                    .desiredDayWeek(careSitter.getDesiredDayWeek())
                    .activityTime(careSitter.getActivityTime())
                    .hourlyWage(careSitter.getDesiredHourlyWage())
                    .monthlyWage(careSitter.getDesiredMonthlyWage())
                    .hopefulRegion(careSitter.getHopefulRegion())
                    .preferredType(careSitter.getPreferredType())
                    .name(member.getName())
                    .birth(member.getBirth())
                    .gender(member.getGender())
                    .storeFileNames(storeFileNames)
                    .build();

            careSitterDTOList.add(dto);

        }

        MyResponse<List<CareSitterListDTO>> body = MyResponse.<List<CareSitterListDTO>>builder()
                .header(StatusEnum.OK)
                .body(careSitterDTOList)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getAllByCareType(String type) {

        List<CareSitter> careSitters = careSitterQueryRepository.findAllByCareType(type);

        if(careSitters.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("해당 지역의 케어시터가 없습니다.")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        List<CareSitterListDTO> careSitterDTOList = new ArrayList<>();


        for (CareSitter careSitter : careSitters) {
            Long id = careSitter.getId();
            Member member = careSitter.getMember();

            List<String> storeFileNames = new ArrayList<>();
            List<CareSitterImage> careSitterImage = careSitterImageRepository.findByCareSitterId(id);
            for (CareSitterImage sitterImage : careSitterImage) {
                String storeFileName = sitterImage.getStoreFileName();
                storeFileNames.add(storeFileName);
            }

            CareSitterListDTO dto = CareSitterListDTO.builder()
                    .id(careSitter.getId())
                    .cctvAgreement(careSitter.getCctvAgreement())
                    .isVaccinated(careSitter.getVaccination())
                    .desiredDayWeek(careSitter.getDesiredDayWeek())
                    .activityTime(careSitter.getActivityTime())
                    .hourlyWage(careSitter.getDesiredHourlyWage())
                    .monthlyWage(careSitter.getDesiredMonthlyWage())
                    .hopefulRegion(careSitter.getHopefulRegion())
                    .preferredType(careSitter.getPreferredType())
                    .name(member.getName())
                    .birth(member.getBirth())
                    .gender(member.getGender())
                    .storeFileNames(storeFileNames)
                    .build();

            careSitterDTOList.add(dto);

        }

        MyResponse<List<CareSitterListDTO>> body = MyResponse.<List<CareSitterListDTO>>builder()
                .header(StatusEnum.OK)
                .body(careSitterDTOList)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getAllByTime(String time) {

        List<CareSitter> careSitters = careSitterQueryRepository.findAllByActivityTime(time);

        if(careSitters.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("해당 지역의 케어시터가 없습니다.")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }

        List<CareSitterListDTO> careSitterDTOList = new ArrayList<>();


        for (CareSitter careSitter : careSitters) {
            Long id = careSitter.getId();
            Member member = careSitter.getMember();

            List<String> storeFileNames = new ArrayList<>();
            List<CareSitterImage> careSitterImage = careSitterImageRepository.findByCareSitterId(id);
            for (CareSitterImage sitterImage : careSitterImage) {
                String storeFileName = sitterImage.getStoreFileName();
                storeFileNames.add(storeFileName);
            }

            CareSitterListDTO dto = CareSitterListDTO.builder()
                    .id(careSitter.getId())
                    .cctvAgreement(careSitter.getCctvAgreement())
                    .isVaccinated(careSitter.getVaccination())
                    .desiredDayWeek(careSitter.getDesiredDayWeek())
                    .activityTime(careSitter.getActivityTime())
                    .hourlyWage(careSitter.getDesiredHourlyWage())
                    .monthlyWage(careSitter.getDesiredMonthlyWage())
                    .hopefulRegion(careSitter.getHopefulRegion())
                    .preferredType(careSitter.getPreferredType())
                    .name(member.getName())
                    .birth(member.getBirth())
                    .gender(member.getGender())
                    .storeFileNames(storeFileNames)
                    .build();

            careSitterDTOList.add(dto);

        }

        MyResponse<List<CareSitterListDTO>> body = MyResponse.<List<CareSitterListDTO>>builder()
                .header(StatusEnum.OK)
                .body(careSitterDTOList)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}

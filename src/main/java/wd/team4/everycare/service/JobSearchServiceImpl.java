package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.domain.Certification;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.CertificationViewDTO;
import wd.team4.everycare.dto.careSitter.CareSitterImageDTO;
import wd.team4.everycare.dto.careSitter.CareSitterListDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobSearchDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobSearchDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareSitterImageRepository;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.CertificationRepository;
import wd.team4.everycare.service.interfaces.JobSearchService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobSearchServiceImpl implements JobSearchService {

    private final CareSitterRepository careSitterRepository;
    private final CertificationRepository certificationRepository;
    private final CareSitterImageRepository careSitterImageRepository;

    @Override
    public ResponseEntity<MyResponse> findAllJobSearch() {
        List<CareSitter> careSitters = careSitterRepository.findAllByDisclosureStatus(1);             //1이나 0이 공개일텐데 그거 따라 숫자 바꿔주기

        if (careSitters.isEmpty()) {
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("케어시터가 없습니다.")
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
    }

    @Override
    public DetailJobSearchDTO findDetailJobSearch(Long id) {
        Optional<CareSitter> findJobSearch = careSitterRepository.findById(id);
        CareSitter careSitter = findJobSearch.get();
        DetailJobSearchDTO jobSearchDTO = careSitter.toDetailJobSearchDTO();

        List<Certification> certifications = certificationRepository.findAllByCareSitterIdAndAdminApproval(careSitter.getId(),1);
        List<CertificationViewDTO> certificationViewDTOs =  new ArrayList<>();

        for (Certification certification : certifications) {
            CertificationViewDTO certificationViewDTO = CertificationViewDTO.builder()
                    .name(certification.getName())
                    .uploadFileName(certification.getUploadFileName())
                    .storeFileName(certification.getStoreFileName())
                    .build();

            certificationViewDTOs.add(certificationViewDTO);
        }

        Long careSitterId = careSitter.getId();

        List<CareSitterImage> careSitterImages = careSitterImageRepository.findByCareSitterId(careSitterId);
        List<CareSitterImageDTO> careSitterImageDTOs = new ArrayList<>();

        for (CareSitterImage careSitterImage: careSitterImages) {
            CareSitterImageDTO careSitterImageDTO = CareSitterImageDTO.builder()
                    .uploadFileName(careSitterImage.getUploadFileName())
                    .storeFileName(careSitterImage.getStoreFileName())
                    .build();

            careSitterImageDTOs.add(careSitterImageDTO);
        }


        DetailJobSearchDTO dto = DetailJobSearchDTO.builder()
                .id(jobSearchDTO.getId())
                .cctvAgreement(jobSearchDTO.getCctvAgreement())
                .is_vaccinated(jobSearchDTO.getIs_vaccinated())
                .desiredDayWeek(jobSearchDTO.getDesiredDayWeek())
                .activityTime(jobSearchDTO.getActivityTime())
                .hourlyWage(jobSearchDTO.getHourlyWage())
                .monthlyWage(jobSearchDTO.getMonthlyWage())
                .hopefulRegion(jobSearchDTO.getHopefulRegion())
                .preferredType(jobSearchDTO.getPreferredType())
                .introduction(jobSearchDTO.getIntroduction())
                .memberDTO(jobSearchDTO.getMemberDTO())
                .certification(certificationViewDTOs)
                .attachFiles(careSitterImageDTOs)
                .build();

        return dto;
    }
}

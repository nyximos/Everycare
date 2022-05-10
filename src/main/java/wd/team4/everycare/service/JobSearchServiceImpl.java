package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.domain.Certification;
import wd.team4.everycare.dto.CertificationViewDTO;
import wd.team4.everycare.dto.careSitter.CareSitterImageDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobSearchDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobSearchDTO;
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
    public List<JobSearchDTO> findAllJobSearch() {
        List<CareSitter> allJobSearch = careSitterRepository.findAllByDisclosureStatus(1);             //1이나 0이 공개일텐데 그거 따라 숫자 바꿔주기
        List<JobSearchDTO> careSitterList = new ArrayList<>();
        allJobSearch.stream().map(careSitter -> careSitter.toJobSearchDTO()).forEach(careSitterList::add);
        return careSitterList;
    }


    @Override
    public DetailJobSearchDTO findDetailJobSearch(String memberId) {
        Optional<CareSitter> findJobSearch = careSitterRepository.findByMemberId(memberId);
        CareSitter careSitter = findJobSearch.get();
        DetailJobSearchDTO jobSearchDTO = careSitter.toDetailJobSearchDTO();

        List<Certification> certifications = certificationRepository.findAllByCareSitterId(careSitter.getId());
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
                .cctvAgreement(jobSearchDTO.getCctvAgreement())
                .is_vaccinated(jobSearchDTO.getIs_vaccinated())
                .desiredDayWeek(jobSearchDTO.getDesiredDayWeek())
                .activityTime(jobSearchDTO.getActivityTime())
                .hourlyWage(jobSearchDTO.getHourlyWage())
                .monthlyWage(jobSearchDTO.getMonthlyWage())
                .hopefulRegion(jobSearchDTO.getHopefulRegion())
                .introduction(jobSearchDTO.getIntroduction())
                .memberDTO(jobSearchDTO.getMemberDTO())
                .certification(certificationViewDTOs)
                .attachFiles(careSitterImageDTOs)
                .build();

        return dto;
    }
}

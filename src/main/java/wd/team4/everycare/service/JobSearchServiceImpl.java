package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.CertificationNameDTO;
import wd.team4.everycare.dto.CertificationViewDTO;
import wd.team4.everycare.dto.badge.BadgeNameDTO;
import wd.team4.everycare.dto.careSitter.CareSitterReviewViewDTO;
import wd.team4.everycare.dto.careSitter.CareSitterImageDTO;
import wd.team4.everycare.dto.careSitter.CareSitterListDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobSearchDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.*;
import wd.team4.everycare.service.interfaces.JobSearchService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JobSearchServiceImpl implements JobSearchService {

    private final CareSitterRepository careSitterRepository;
    private final CertificationRepository certificationRepository;
    private final CareSitterImageRepository careSitterImageRepository;
    private final CareSitterBadgeRepository careSitterBadgeRepository;
    private final CareSitterReviewRepository careSitterReviewRepository;

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


            List<BadgeNameDTO> careSitterBadgeDTOs = new ArrayList<>();
            List<CareSitterBedge> careSitterBadges = careSitterBadgeRepository.findByCareSitterId(id);
            for (CareSitterBedge careSitterBedge : careSitterBadges) {
                Long badgeId = careSitterBedge.getBedge().getId();
                String filePath = careSitterBedge.getBedge().getFilePath();
                String fileName = careSitterBedge.getBedge().getFileName();

                BadgeNameDTO badgeNameDTO = BadgeNameDTO.builder()
                        .id(badgeId)
                        .filePath(filePath)
                        .fileName(fileName)
                        .build();

                careSitterBadgeDTOs.add(badgeNameDTO);
            }

            List<CertificationNameDTO> certificationNameDTOs = new ArrayList<>();
            List<Certification> certifications = certificationRepository.findAllByCareSitterId(id);
            for (Certification certification:certifications) {
                Long certificationId = certification.getId();
                String name = certification.getName();
                String storeFileName = certification.getStoreFileName();
                String uploadFileName = certification.getUploadFileName();
                int adminApproval = certification.getAdminApproval();

                CertificationNameDTO certificationNameDTO = CertificationNameDTO.builder()
                        .id(certificationId)
                        .name(name)
                        .storeFileName(storeFileName)
                        .uploadFileName(uploadFileName)
                        .adminApproval(adminApproval)
                        .build();

                certificationNameDTOs.add(certificationNameDTO);
            }

            CareSitterListDTO dto = CareSitterListDTO.builder()
                    .id(careSitter.getId())
                    .age(careSitter.calculateAge(careSitter.getMember().getBirth(), LocalDate.now()))
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
                    .badge(careSitterBadgeDTOs)
                    .certification(certificationNameDTOs)
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
    public ResponseEntity<MyResponse> findDetailJobSearch(Long id) {
        CareSitter careSitter = careSitterRepository.findById(id).orElse(null);
        DetailJobSearchDTO jobSearchDTO = careSitter.toDetailJobSearchDTO();

        List<Certification> certifications = certificationRepository.findAllByCareSitterIdAndAdminApproval(careSitter.getId(), 1);
        List<CertificationViewDTO> certificationViewDTOs = new ArrayList<>();

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

        for (CareSitterImage careSitterImage : careSitterImages) {
            CareSitterImageDTO careSitterImageDTO = CareSitterImageDTO.builder()
                    .id(careSitterImage.getId())
                    .uploadFileName(careSitterImage.getUploadFileName())
                    .storeFileName(careSitterImage.getStoreFileName())
                    .build();

            careSitterImageDTOs.add(careSitterImageDTO);
        }

        List<BadgeNameDTO> careSitterBadgeDTOs = new ArrayList<>();
        List<CareSitterBedge> careSitterBadges = careSitterBadgeRepository.findByCareSitterId(careSitterId);
        for (CareSitterBedge careSitterBedge : careSitterBadges) {
            Long badgeId = careSitterBedge.getBedge().getId();
            String filePath = careSitterBedge.getBedge().getFilePath();
            String fileName = careSitterBedge.getBedge().getFileName();

            BadgeNameDTO badgeNameDTO = BadgeNameDTO.builder()
                    .id(badgeId)
                    .filePath(filePath)
                    .fileName(fileName)
                    .build();

            careSitterBadgeDTOs.add(badgeNameDTO);
        }

        List<CareSitterReviewViewDTO> careSitterReviewDTOs = new ArrayList<>();
        List<CareSitterReview> careSitterReviewList = careSitterReviewRepository.findByCareSitter(careSitter);
        for (CareSitterReview careSitterReview: careSitterReviewList) {
            Long rating = careSitterReview.getRating();
            String comment = careSitterReview.getComment();
            ActivityClassification activityClassification = careSitterReview.getActivityClassification();
            Member member = careSitterReview.getMember();

            CareSitterReviewViewDTO reviewViewDTO = CareSitterReviewViewDTO.builder()
                    .rating(rating)
                    .comment(comment)
                    .activityClassification(activityClassification.toDTO())
                    .member(member.toMemberNameDTO())
                    .build();

            careSitterReviewDTOs.add(reviewViewDTO);
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
                .badge(careSitterBadgeDTOs)
                .careSitterReviews(careSitterReviewDTOs)
                .build();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("케어시터상세조회")
                .body(dto)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}

package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.ImageDTO;
import wd.team4.everycare.dto.careNote.ActivityInformationDTO;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.contract.ContractDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.*;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.*;
import wd.team4.everycare.repository.query.JobOfferQueryRepository;
import wd.team4.everycare.service.interfaces.JobOfferService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
    private final CareTargetRepository careTargetRepository;
    private final ContractRepository contractRepository;
    private final CareSitterRepository careSitterRepository;
    private final CareSitterImageRepository careSitterImageRepository;
    private final ActivityInformationRepository activityInformationRepository;
    private final JobOfferCareSitterRepository jobOfferCareSitterRepository;
    private final JobOfferQueryRepository jobOfferQueryRepository;
    private final CareTargetImageRepository careTargetImageRepository;

    @Override
    public ResponseEntity<MyResponse> getJobOffer() {
        List<JobOffer> allList = jobOfferRepository.findAll();


        List<JobOfferListDTO> jobOfferDTOs = new ArrayList<>();
        List<ImageDTO> imageDTO = new ArrayList<>();
        allList.stream().map(jobOffer -> jobOffer.toJobOfferListDTO(jobOffer)).forEach(jobOfferDTOs::add);

        for (JobOfferListDTO jobOfferDTO : jobOfferDTOs) {
            JobOfferCareTargetDTO careTargetDTO = jobOfferDTO.getCareTarget();
            CareTarget careTarget = careTargetDTO.toCareTarget();
            List<CareTargetImage> allByCareTarget = careTargetImageRepository.findAllByCareTarget(careTarget);
            allByCareTarget.stream().map(image -> image.toImageDTO()).forEach(imageDTO::add);

            jobOfferDTO.setCareTargetImageList(allByCareTarget);
        }

        MyResponse body = MyResponse.builder()
                .message("전체 구인 조회")
                .header(StatusEnum.OK)
                .body(jobOfferDTOs)
                .build();

        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getDetailJobOffer(Long id) {
        JobOffer findJobOffer = jobOfferRepository.findById(id).get();

        DetailJobOfferDTO detailJobOfferDTO = DetailJobOfferDTO.builder()
                .memberId(findJobOffer.getMember().getId())
                .title(findJobOffer.getTitle())
                .startDate(String.valueOf(findJobOffer.getStartDate()))
                .endDate(String.valueOf(findJobOffer.getEndDate()))
                .desiredDayWeek(findJobOffer.getDay())
                .desiredStartTime(findJobOffer.getDesiredStartTime())
                .desiredEndTime(findJobOffer.getDesiredEndTime())
                .pay(findJobOffer.getPay())
                .comment(findJobOffer.getComment())
                .desiredCareSitterGender(findJobOffer.getDesiredCareSitterGender())
                .careTarget(findJobOffer.getCareTarget().toJobOfferCareTargetDTO())
                .careTargetScheduleListDTO(findJobOffer.toJobOfferDTO().getCareTargetSchedule())
                .build();

        Long careTargetScheduleId = findJobOffer.getCareTargetSchedule().getId();
        List<ActivityInformation> activityInformation = activityInformationRepository.findAllByCareTargetScheduleId(careTargetScheduleId);

        List<ActivityInformationDTO> activityInformationDTOs = new ArrayList<>();

        activityInformation.stream().map(activity -> activity.toActivityInformationDTO()).forEach(activityInformationDTOs::add);

        detailJobOfferDTO.setActivityInformationDTO(activityInformationDTOs);

        CareTarget careTarget = findJobOffer.getCareTarget();
        List<CareTargetImage> careTargetImages = careTargetImageRepository.findAllByCareTarget(careTarget);
        List<ImageDTO> imageDTOs = new ArrayList<>();
        careTargetImages.stream().map(image -> image.toImageDTO()).forEach(imageDTOs::add);

        detailJobOfferDTO.setCareTargetImages(careTargetImages);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("구인글 상세조회")
                .body(detailJobOfferDTO)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public List<CareTargetScheduleListDTO> findSchedule(Long id) {
        List<CareTargetSchedule> findCareTargetSchedule = careTargetScheduleRepository.findAllByCareTargetId(id);
        List<CareTargetScheduleListDTO> careTargetScheduleListDTOs = new ArrayList<>();
        findCareTargetSchedule.stream().map(careTargetSchedule -> careTargetSchedule.toListDTO()).forEach(careTargetScheduleListDTOs::add);
        return careTargetScheduleListDTOs;
    }

    @Override
    public List<CareTargetFormDTO> findCareTarget(String id) {
        List<CareTarget> findCareTarget = careTargetRepository.findAllByMemberId(id);
        List<CareTargetFormDTO> careTargetDTOs = new ArrayList<>();
        findCareTarget.stream().map(careTarget -> careTarget.toFormDTO()).forEach(careTargetDTOs::add);
        System.out.println("findCareTarget = " + findCareTarget);
        return careTargetDTOs;
    }

    @Override
    public JobOffer save(PrincipalDetails principalDetails, JobOfferDTO jobOfferDTO) {
        Member member = principalDetails.getUser();
        Long careTargetId = jobOfferDTO.getCareTarget().getId();
        Long scheduleId = jobOfferDTO.getCareTargetSchedule().getId();

        CareTarget careTarget = careTargetRepository.findById(careTargetId).orElse(null);
        CareTargetSchedule careTargetSchedule = careTargetScheduleRepository.findById(scheduleId).orElse(null);
        CareSitter careSitter = careSitterRepository.findByMember(member);

        JobOffer jobOffer = JobOffer.builder()
                .title(jobOfferDTO.getTitle())
                .startDate(jobOfferDTO.getStartDate())
                .endDate(jobOfferDTO.getEndDate())
                .day(jobOfferDTO.getDesiredDayWeek())
                .desiredEndTime(jobOfferDTO.getDesiredEndTime())
                .pay(jobOfferDTO.getPay())
                .amount(jobOfferDTO.getAmount())
                .desiredCareSitterGender(jobOfferDTO.getDesiredCareSitterGender())
                .comment(jobOfferDTO.getComment())
                .member(principalDetails.getUser())
                .careTarget(careTarget)
                .careTargetSchedule(careTargetSchedule)
                .build();

        JobOfferCareSitter jobOfferCareSitter = JobOfferCareSitter.builder()
                .jobOffer(jobOffer)
                .careSitter(careSitter)
                .build();

        jobOfferCareSitterRepository.save(jobOfferCareSitter);

        JobOffer saveJobOffer = jobOfferRepository.save(jobOffer);
        return saveJobOffer;
    }

    @Override
    public String update(Long id, JobOfferDTO jobOfferDTO) {
        Optional<JobOffer> jobOffer = jobOfferRepository.findById(id);
        JobOffer jobOfferEntity = jobOffer.orElse(null);
        System.out.println("jobOfferEntity = " + jobOfferEntity.getTitle());
        if (jobOfferEntity == null) {
            System.out.println("구인글 없음");
            return "실패";
        } else {
            jobOfferEntity.updateInfo(jobOfferDTO);
            return "완료";
        }
    }

    @Override
    public void deleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<MyResponse> offer(Long jobOfferId, PrincipalDetails principalDetails) {
        Member member = principalDetails.getUser();
        CareSitter careSitter = careSitterRepository.findByMember(member);
        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId).orElse(null);

        if (jobOffer != null) {
            Contract contract = Contract.builder()
                    .name(jobOffer.getCareTarget().getName() + "_" + careSitter.getName())
                    .startDate(jobOffer.getStartDate())
                    .endDate(jobOffer.getEndDate())
                    .startTime(jobOffer.getDesiredStartTime())
                    .endTime(jobOffer.getDesiredEndTime())
                    .pay(jobOffer.getPay())
                    .status(1)
                    .jobOffer(jobOffer)
                    .member(jobOffer.getMember())               //구인글에서 회원 찾아서 계약서에 넣기 (원래는 케어시터였음)
                    .careSitter(careSitter)
                    .build();

            contractRepository.save(contract);
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("성공")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        } else return null;

    }

    @Override
    public ResponseEntity<MyResponse> findOffer(Long jobOfferId, PrincipalDetails principalDetails) {

        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId).orElse(null);

        if (jobOffer != null) {
            List<Contract> findOffers = contractRepository.findByStatusAndJobOffer(1, jobOffer);
            List<ContractDTO> contractDTOs = new ArrayList<>();
            findOffers.stream().map(contract -> contract.toContractDTO()).forEach(contractDTOs::add);
            List<CareSitterImage> careSitterImageList = new ArrayList<>();

            System.out.println("findOffers = " + findOffers);

            for (ContractDTO contractDTO : contractDTOs) {
                Long careSitterId = contractDTO.getCareSitterDTO().getId();
                List<CareSitterImage> careSitterImages = careSitterImageRepository.findByCareSitterId(careSitterId);
                for (CareSitterImage careSitterImage : careSitterImages) {
                    CareSitterImage careSitterImage1 = CareSitterImage.builder()
                            .uploadFileName(careSitterImage.getUploadFileName())
                            .storeFileName(careSitterImage.getStoreFileName())
                            .build();
                    careSitterImageList.add(careSitterImage1);
                }
                contractDTO.setCareSitterImage(careSitterImageList);
            }
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .body(contractDTOs)
                    .message("조회 성공")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        } else return null;
    }


    @Override
    public ResponseEntity<MyResponse> findDetailOffer(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElse(null);
        if (contract != null) {
            ContractDTO contractDTO = contract.toContractDTO();

            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .body(contractDTO)
                    .message("상세 조회 성공")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        } else return null;
    }

    @Override
    public ResponseEntity<MyResponse> searchPay(int min, int max) {
        List<JobOffer> findByPay = jobOfferQueryRepository.findAllByPay(min, max);

        if (findByPay.isEmpty()) {
            return null;
        } else {
            List<JobOfferDTO> jobOfferDTOs = new ArrayList<>();
            findByPay.stream().map(jobOffer -> jobOffer.toJobOfferDTO()).forEach(jobOfferDTOs::add);

            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("가격에 따른 조회")
                    .body(jobOfferDTOs)
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> searchRegion(String region) {
        List<JobOffer> findByRegion = jobOfferQueryRepository.findAllByRegion(region);

        if (findByRegion.isEmpty()) {
            return null;
        } else {
            List<JobOfferDTO> jobOfferDTOs = new ArrayList<>();
            findByRegion.stream().map(jobOffer -> jobOffer.toJobOfferDTO()).forEach(jobOfferDTOs::add);
            System.out.println("jobOfferDTOs = " + jobOfferDTOs);
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("지역에 따른 조회")
                    .body(jobOfferDTOs)
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> searchDate(String dates) {

        String[] date = dates.split(",");
        LocalDate startDate = StringToLocalDateTime(date[0]);
        LocalDate endDate = StringToLocalDateTime(date[1]);
        
        System.out.println("endDate = " + startDate);
        System.out.println("endDate = " + endDate);
        List<JobOffer> findByDate = jobOfferQueryRepository.findAllByDate(startDate, endDate);
        System.out.println("findByDate = " + findByDate);
        if (findByDate.isEmpty()) {
            return null;
        }else{
            List<JobOfferDTO> jobOfferDTOs = new ArrayList<>();
            findByDate.stream().map(jobOffer -> jobOffer.toJobOfferDTO()).forEach(jobOfferDTOs::add);

            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("날짜에 따른 조회")
                    .body(jobOfferDTOs)
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<MyResponse> announceOffer(PrincipalDetails principalDetails) {
        Member member = principalDetails.getUser();
        List<Contract> contractList = contractRepository.findByStatusAndMember(1, member);

        if(contractList.isEmpty()){
            return null;
        }else{
            List<AnnounceJobOfferDTO> jobOfferDTOs = new ArrayList<>();

            for (Contract contract:contractList) {
                JobOffer jobOffer = contract.getJobOffer();
                CareSitter careSitter = contract.getCareSitter();

                AnnounceJobOfferDTO announceJobOfferDTO = AnnounceJobOfferDTO.builder()
                        .careSitter(careSitter.toCareSitterDTO())
                        .jobOffer(jobOffer.toJobOfferDTO())
                        .build();

                jobOfferDTOs.add(announceJobOfferDTO);
            }

            MyResponse body = MyResponse.builder()
                    .message("알림")
                    .header(StatusEnum.OK)
                    .body(jobOfferDTOs)
                    .build();
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

    private LocalDate StringToLocalDateTime(String localDateTimeStr) {

        LocalDate date = LocalDate.parse(localDateTimeStr, DateTimeFormatter.ISO_DATE);
        return date;
    }

}

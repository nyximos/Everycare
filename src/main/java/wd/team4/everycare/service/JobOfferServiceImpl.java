package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.contract.ContractDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobOfferDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobOfferDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.*;
import wd.team4.everycare.service.interfaces.JobOfferService;

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

    @Override
    public List<JobOfferDTO> getJobOffer() {
        List<JobOffer> allList = jobOfferRepository.findAll();
        List<JobOfferDTO> jobOfferDTOs = new ArrayList<>();
        allList.stream().map(jobOffer -> jobOffer.toJobOfferDTO()).forEach(jobOfferDTOs::add);
        return jobOfferDTOs;
    }

    @Override
    public DetailJobOfferDTO getDetailJobOffer(Long id) {
        JobOffer findJobOffer = jobOfferRepository.findById(id).get();
        DetailJobOfferDTO jobOfferDTO = findJobOffer.toDetailJobOfferDTO(findJobOffer);
        return jobOfferDTO;
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
        Long careTargetId = jobOfferDTO.getCareTarget().getId();
        Long scheduleId = jobOfferDTO.getCareTargetSchedule().getId();

        CareTarget careTarget = careTargetRepository.findById(careTargetId).orElse(null);
        CareTargetSchedule careTargetSchedule = careTargetScheduleRepository.findById(scheduleId).orElse(null);

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
                    .member(member)
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


}

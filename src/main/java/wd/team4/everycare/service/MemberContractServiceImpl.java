package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Contract;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.contract.JobOfferContractListDTO;
import wd.team4.everycare.dto.product.ProductListViewDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.*;
import wd.team4.everycare.service.interfaces.MemberContractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberContractServiceImpl implements MemberContractService {

    private final CareSitterRepository careSitterRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ContractRepository contractRepository;


    @Override
    public ResponseEntity<MyResponse> removeContract(Long id) {
        contractRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> saveContract(PrincipalDetails principalDetails, Long jobOfferId, Long careSitterId) {

        // 멤버 찾기
        Member member = principalDetails.getUser();

        // 케어시터 찾기
        Optional<CareSitter> careSitter = careSitterRepository.findById(careSitterId);
        CareSitter careSitterEntity = careSitter.orElse(null);
        String careSitterName = careSitterEntity.getName();

        // 구인글 찾기
        Optional<JobOffer> jobOffer = jobOfferRepository.findById(jobOfferId);
        JobOffer jobOfferEntity = jobOffer.orElse(null);
        String careTargetName = jobOfferEntity.getCareTarget().getName();

        // 계약서 생성
        Contract contract = Contract.builder()
                .name(careTargetName + "_" + careSitterName)
                .startDate(jobOfferEntity.getStartDate())
                .endDate(jobOfferEntity.getEndDate())
                .startTime(jobOfferEntity.getDesiredStartTime())
                .endTime(jobOfferEntity.getDesiredEndTime())
                .pay(jobOfferEntity.getPay())
                .amount(jobOfferEntity.getAmount())
                .status(0)
                .jobOffer(jobOfferEntity)
                .member(member)
                .careSitter(careSitterEntity)
                .careTarget(jobOfferEntity.getCareTarget())
                .build();

        contractRepository.save(contract);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    public ResponseEntity<MyResponse> getRecruitions(PrincipalDetails principalDetails) {

        Member member = principalDetails.getUser();

        List<JobOffer> jobOffers = jobOfferRepository.findAllByMember(member);
        List<JobOfferContractListDTO> jobOfferContractListDTOs = new ArrayList<>();

        for (JobOffer jobOffer : jobOffers) {
            JobOfferContractListDTO dto = JobOfferContractListDTO.builder()
                    .id(jobOffer.getId())
                    .title(jobOffer.getTitle())
                    .startDate(jobOffer.getStartDate())
                    .endDate(jobOffer.getEndDate())
                    .startTime(jobOffer.getDesiredStartTime())
                    .endTime(jobOffer.getDesiredEndTime())
                    .careTargetName(jobOffer.getCareTarget().getName())
                    .scheduleName(jobOffer.getCareTargetSchedule().getName())
                    .build();

            jobOfferContractListDTOs.add(dto);
        }

        MyResponse<List<JobOfferContractListDTO>> body = MyResponse.<List<JobOfferContractListDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(jobOfferContractListDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}

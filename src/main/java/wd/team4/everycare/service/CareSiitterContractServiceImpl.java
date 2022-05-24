package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.dto.ImageDTO;
import wd.team4.everycare.dto.caretarget.CareTargetDetailDTO;
import wd.team4.everycare.dto.contract.CareSitterContractDTO;
import wd.team4.everycare.dto.contract.CareSitterContractListDTO;
import wd.team4.everycare.dto.contract.ContractDTO;
import wd.team4.everycare.dto.contract.ContractJobOfferDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareTargetImageRepository;
import wd.team4.everycare.repository.ContractRepository;
import wd.team4.everycare.service.interfaces.ContractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CareSiitterContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final CareTargetImageRepository careTargetImageRepository;

    @Override
    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {

        CareSitter careSitter = principalDetails.getCareSitter();

        List<Contract> contracts = contractRepository.findByCareSitterAndStatus(careSitter, 0);

        List<CareSitterContractListDTO> careSitterContractListDTOs = new ArrayList<>();

        for (Contract contract : contracts) {

            CareSitterContractListDTO dto = CareSitterContractListDTO.builder()
                    .id(contract.getId())
                    .title(contract.getJobOffer().getTitle())
                    .startDate(contract.getStartDate())
                    .endDate(contract.getEndDate())
                    .amount(contract.getAmount())
                    .build();

            careSitterContractListDTOs.add(dto);

        }

        MyResponse<List<CareSitterContractListDTO>> body = MyResponse.<List<CareSitterContractListDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careSitterContractListDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> get(Long id) {
        Optional<Contract> contract = contractRepository.findById(id);
        Contract contractEntity = contract.orElse(null);

        JobOffer jobOffer = contractEntity.getJobOffer();
        CareTarget careTarget = jobOffer.getCareTarget();
        List<CareTargetImage> careTargetImages = careTargetImageRepository.findAllByCareTarget(careTarget);
        List<ImageDTO> imageDTOs = new ArrayList<>();

        ContractJobOfferDTO contractJobOfferDTO = ContractJobOfferDTO.builder()
                .id(jobOffer.getId())
                .title(jobOffer.getTitle())
                .startDate(jobOffer.getStartDate())
                .endDate(jobOffer.getEndDate())
                .day(jobOffer.getDay())
                .desiredStartTime(jobOffer.getDesiredStartTime())
                .desiredEndTime(jobOffer.getDesiredEndTime())
                .amount(jobOffer.getAmount())
                .build();

        for (CareTargetImage careTargetImage : careTargetImages) {
            ImageDTO imageDTO = ImageDTO.builder()
                    .id(careTargetImage.getId())
                    .uploadFileName(careTargetImage.getUploadFileName())
                    .storeFileName(careTargetImage.getStoreFileName())
                    .build();

            imageDTOs.add(imageDTO);
        }

        CareTargetDetailDTO careTargetDetailDTO = CareTargetDetailDTO.builder()
                .id(careTarget.getId())
                .name(careTarget.getName())
                .gender(careTarget.getGender())
                .birth(careTarget.getBirth())
                .height(careTarget.getHeight())
                .weight(careTarget.getWeight())
                .zipcode(careTarget.getZipcode())
                .address(careTarget.getAddress())
                .detailedAddress(careTarget.getDetailedAddress())
                .longTermCareGrade(careTarget.getLongTermCareGrade())
                .comment(careTarget.getComment())
                .pet(careTarget.getPet())
                .isCctvAgreement(careTarget.getIsCctvAgreement())
                .careType(careTarget.getCareType())
                .coronaTest(careTarget.getCoronaTest())
                .imageDTOs(imageDTOs)
                .build();

        CareSitterContractDTO careSitterContractDTO = CareSitterContractDTO.builder()
                .contractJobOfferDTO(contractJobOfferDTO)
                .careTargetDetailDTO(careTargetDetailDTO)
                .build();

        MyResponse<CareSitterContractDTO> body = MyResponse.<CareSitterContractDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careSitterContractDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> approve(Long id) {

        Optional<Contract> contract = contractRepository.findById(id);
        Contract contractEntity = contract.orElse(null);

        contractEntity.approveCareSitter();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> getCompleteContract(Long id) {
        ContractDTO
        return null;
    }

}

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
import wd.team4.everycare.dto.contract.CareSitterContractListDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.ContractRepository;
import wd.team4.everycare.service.interfaces.ContractService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CareSiitterContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {

        CareSitter careSitter = principalDetails.getCareSitter();

        List<Contract> contracts = contractRepository.findByCareSitterAndStatus(careSitter, 0);

        List<CareSitterContractListDTO> careSitterContractListDTOs = new ArrayList<>();

        for (Contract contract : contracts) {

            CareSitterContractListDTO dto = CareSitterContractListDTO.builder()
                    .id(contract.getJobOffer().getId())
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

}

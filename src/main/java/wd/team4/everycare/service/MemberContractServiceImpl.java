package wd.team4.everycare.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Contract;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.PayResponse;
import wd.team4.everycare.dto.contract.ContractDTO;
import wd.team4.everycare.dto.contract.SignContractDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.repository.ContractRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.service.interfaces.MemberContractService;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberContractServiceImpl implements MemberContractService {

    private final CareSitterRepository careSitterRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ContractRepository contractRepository;
    private final PaymentServiceImpl paymentService;


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
                .build();

        contractRepository.save(contract);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyResponse> signContract(PayResponse payResponse, Long contractId) {
        LocalDateTime payDateTime = payResponse.getApprovedAt();
        int amount = payResponse.getTotalAmount();
        String cardCompany = payResponse.getCardCompany();
        String cardNumber = payResponse.getCardNumber();
        String payApprove = payResponse.getApproveNo();
        int monthlyInstallmentPlan = payResponse.getInstallmentPlanMonths();

        Contract contract = contractRepository.findById(contractId).orElse(null);
        contract.updateContract(payDateTime, amount, cardCompany, cardNumber, payApprove, monthlyInstallmentPlan);

        ContractDTO contractDTO = contract.toContractDTO();

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("DB업데이트")
                .body(contractDTO)
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }


//    @Override
//    public ResponseEntity<MyResponse> signContract(PrincipalDetails principalDetails, String paymentKey, String orderId, Long amount) throws IOException {
//
//        Member member = principalDetails.getUser();
//
//        List<JobOffer> findJobOffers = jobOfferRepository.findByMember(member);
//
//        HttpHeaders headers = new HttpHeaders();
//        // headers.setBasicAuth(SECRET_KEY, ""); // spring framework 5.2 이상 버전에서 지원
//        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        //map에 필요한 데이터 집어넣기(payments에 필요한 데이터)
//        Map<String, String> payloadMap = new HashMap<>();
//        payloadMap.put("orderId", String.valueOf(orderId));
//        payloadMap.put("amount", String.valueOf(amount));
//
//        //첫번재 파라미터로 http body, 두번째로 http header
//        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);
//
////        String url, @Nullable Object request, Class<T> responseType, Object... uriVariables
//        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity("https://api.tosspayments.com/v1/payments/" + paymentKey, request, JsonNode.class);
//        Contract contract;
//
//        for (JobOffer jobOffer : findJobOffers) {
//            CareSitter careSitter = careSitterRepository.findByMember(member);
//            Contract findContract = contractRepository.findByCareSitterAndStatusAndJobOffer(careSitter, 2, jobOffer).orElse(null);
//            if (findContract == null) {
//                continue;
//            } else {
//                if (responseEntity.getStatusCode() == HttpStatus.OK) {
//                    JsonNode successNode = responseEntity.getBody();
//
//                    String approvedAt = successNode.get("approvedAt").asText();
//                    LocalDateTime payDateTime = StringToLocalDateTime(approvedAt);
//                    int totalAmount = successNode.get("totalAmount").asInt();
//                    String cardCompany = successNode.get("card").get("company").asText();
//                    String cardNumber = successNode.get("card").get("number").asText();
//                    String payApprove = successNode.get("card").get("approveNo").asText();
//                    int monthlyInstallmentPlan = successNode.get("card").get("installmentPlanMonths").asInt();
//
//                    contract = Contract.builder()
//                            .id(findContract.getId())
//                            .name(findContract.getName())
//                            .startDate(findContract.getStartDate())
//                            .endDate(findContract.getEndDate())
//                            .startTime(findContract.getStartTime())
//                            .endTime(findContract.getEndTime())
//                            .pay(findContract.getPay())
//                            .status(3)
//                            .amount(totalAmount)
//                            .payDatetime(payDateTime)
//                            .cardCompany(cardCompany)
//                            .cardNumber(cardNumber)
//                            .payApprove(payApprove)
//                            .monthlyInstallmentPlan(monthlyInstallmentPlan)
//                            .jobOffer(findContract.getJobOffer())
//                            .member(findContract.getMember())
//                            .careSitter(findContract.getCareSitter())
//                            .build();
//
//                    MyResponse body = MyResponse.builder()
//                            .header(StatusEnum.OK)
//                            .message("결제성공")
//                            .body(contract)
//                            .build();
//
//                    return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
//                } else {
//                    JsonNode failNode = responseEntity.getBody();
//                    MyResponse body = MyResponse.builder()
//                            .header(StatusEnum.BAD_REQUEST)
//                            .body(failNode)
//                            .message("실패")
//                            .build();
//                    return new ResponseEntity<MyResponse>(body, HttpStatus.BAD_REQUEST);
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<MyResponse> orderContract(PrincipalDetails principalDetails, SignContractDTO signContractDTO) {
//
//        Contract findContract = contractRepository.findById(signContractDTO.getId()).orElse(null);
//        findContract.updateStatus(findContract);
//
//        if (findContract != null) {
//            ContractDTO contractDTO = findContract.toContractDTO();
//
//            MyResponse body = MyResponse.builder()
//                    .header(StatusEnum.OK)
//                    .message("상태 코드2로 업데이트")
//                    .body(contractDTO)
//                    .build();
//            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
//        } else {
//            return null;
//        }
//
//    }
}

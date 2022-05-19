package wd.team4.everycare.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import wd.team4.everycare.dto.PayResponse;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.interfaces.PaymentService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String SECRET_KEY = "test_sk_7DLJOpm5QrlDBWj6LE5rPNdxbWnY";

    @Override
    public PayResponse payment(String paymentKey, String orderId, Long amount) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(SECRET_KEY, ""); // spring framework 5.2 이상 버전에서 지원
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        //map에 필요한 데이터 집어넣기(payments에 필요한 데이터)
        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));

        //첫번재 파라미터로 http body, 두번째로 http header
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);

//        String url, @Nullable Object request, Class<T> responseType, Object... uriVariables
        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity("https://api.tosspayments.com/v1/payments/" + paymentKey, request, JsonNode.class);

        String secret;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            JsonNode successNode = responseEntity.getBody();
            secret = successNode.get("secret").asText();

            String resPaymentKey = successNode.get("paymentKey").asText();
            String resOrderId = successNode.get("orderId").asText();
            String method = successNode.get("method").asText();
            String status = successNode.get("status").asText();

            String requestAtStr = successNode.get("requestedAt").asText();
            LocalDateTime requestAt = StringToLocalDateTime(requestAtStr);

            String approvedAtStr = successNode.get("approvedAt").asText();
            LocalDateTime approvedAt = StringToLocalDateTime(approvedAtStr);

            boolean useEscrow = successNode.get("useEscrow").asBoolean();
            String cardCompany = successNode.get("card").get("company").asText();
            String cardNumber = successNode.get("card").get("number").asText();
            int installmentPlanMonths = successNode.get("card").get("installmentPlanMonths").asInt();
            boolean isInterestFree = successNode.get("card").get("isInterestFree").asBoolean();
            String approveNo = successNode.get("card").get("approveNo").asText();
            boolean useCardPoint = successNode.get("card").get("useCardPoint").asBoolean();
            String cardType = successNode.get("card").get("cardType").asText();
            String ownerType = successNode.get("card").get("ownerType").asText();
            String acquireStatus = successNode.get("card").get("acquireStatus").asText();
            int totalAmount = successNode.get("totalAmount").asInt();
            int balanceAmount = successNode.get("balanceAmount").asInt();

            PayResponse payResponse = PayResponse.builder()
                    .paymentKey(resPaymentKey)
                    .orderId(resOrderId)
                    .method(method)
                    .status(status)
                    .requestAt(requestAt)
                    .approvedAt(approvedAt)
                    .useEscrow(useEscrow)
                    .cardCompany(cardCompany)
                    .cardNumber(cardNumber)
                    .installmentPlanMonths(installmentPlanMonths)
                    .isInterestFree(isInterestFree)
                    .approveNo(approveNo)
                    .useCardPoint(useCardPoint)
                    .cardType(cardType)
                    .ownerType(ownerType)
                    .acquireStatus(acquireStatus)
                    .totalAmount(totalAmount)
                    .balanceAmount(balanceAmount)
                    .build();

            System.out.println("payResponse = " + payResponse);
            return payResponse;
        } else {
            JsonNode failNode = responseEntity.getBody();
            return null;
        }

    }


    private LocalDateTime StringToLocalDateTime(String LocalDateTimeStr) {

        LocalDateTime dateTime = LocalDateTime.from(
                Instant.from(
                        DateTimeFormatter.ISO_DATE_TIME.parse(LocalDateTimeStr)
                ).atZone(ZoneId.of("Asia/Seoul"))
        );
        return dateTime;
    }
}

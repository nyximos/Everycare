package wd.team4.everycare.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import wd.team4.everycare.domain.Test;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestApiController {

    //키 값 형태의
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String SECRET_KEY = "test_sk_7DLJOpm5QrlDBWj6LE5rPNdxbWnY";

    @ResponseBody
    @GetMapping("/test")
    public Test test(){
        System.out.println("test");
        Test test = new Test();
        test.setId("1");
        test.setName("test");
        return test;
    }

    //  @ModelAttribute
    @PostMapping("/test")
    public ResponseEntity<MyResponse> postTest(@ModelAttribute TestDto testDto){
        System.out.println("컨트롤러");
        Test test = new Test();
        test.setId(testDto.getId());
        test.setName(testDto.getName());
        System.out.println("test.getId() = " + test.getId());
        System.out.println("test.getName() = " + test.getName());

        MyResponse<Test> body = MyResponse.<Test>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(test)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }


    @Getter @Setter
    static class TestDto {
        private String id;
        private String name;
    }


    /*  @RequestParam
    @PostMapping("/test")
    public ResponseEntity<MyResponse> postTest(@RequestParam("id") String id){
        System.out.println("id = " + id);

        Test test = new Test();
        test.setId(id);
        test.setName("test");


        MyResponse<Test> body = MyResponse.<Test>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(test)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
     */

    /*  @RequestBody Dto
    @PostMapping("/test")
    public ResponseEntity<MyResponse> postTest(@RequestBody Test testDTO){

        Test test = new Test();
        test.setId(testDTO.getId());
        test.setName("test");


        MyResponse<Test> body = MyResponse.<Test>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(test)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
    */


    /*  @RequestBody HashMap
    @PostMapping("/test")
    public ResponseEntity<MyResponse> postTest(@RequestBody HashMap<String, String> map){
        String id = map.get("id");
        System.out.println("id = " + id);

        Test test = new Test();
        test.setId(id);
        test.setName("test");


        MyResponse<Test> body = MyResponse.<Test>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(test)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

     */

    @GetMapping("/hello")
    public void hello() {
        System.out.println("HelloWorld");
    }

    @RequestMapping("/success")
    public ResponseEntity<MyResponse> confirmPayment(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount,
                                                     @RequestParam String contractId) throws IOException {
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
            String approvedAt = successNode.get("approvedAt").asText();
            LocalDateTime localDateTime = StringToLocalDateTime(approvedAt);
            int amounts = successNode.get("totalAmount").asInt();
            String cardCompany = successNode.get("card").get("company").asText();
            String cardNumber = successNode.get("card").get("number").asText();
            String payApprove = successNode.get("card").get("approveNo").asText();
            int monthlyInstallmentPlan = successNode.get("card").get("installmentPlanMonths").asInt();

            System.out.println("successNode = " + successNode);

            System.out.println("monthlyInstallmentPlan = " + monthlyInstallmentPlan);
            System.out.println("payApprove = " + payApprove);
            System.out.println("cardNumber = " + cardNumber);
            System.out.println("cardCompany = " + cardCompany);
            System.out.println("amounts = " + amounts);
            System.out.println("localDateTime = " + localDateTime);

            MyResponse body = MyResponse.builder()
                    .message("결제 내역")
                    .body(successNode)
                    .header(StatusEnum.OK)
                    .build();

            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
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
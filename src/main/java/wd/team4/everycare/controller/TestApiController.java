package wd.team4.everycare.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import wd.team4.everycare.domain.Test;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class TestApiController {

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

    @GetMapping("/success")
    public String confirmPayment(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount) throws IOException, InterruptedException {

        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(SECRET_KEY, ""); // spring framework 5.2 이상 버전에서 지원
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://api.tosspayments.com/v1/payments/" + paymentKey, request, JsonNode.class);

        String secret;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            JsonNode successNode = responseEntity.getBody();
            secret = successNode.get("secret").asText();
            return "success";
        } else {
            JsonNode failNode = responseEntity.getBody();
            return "fail";
        }

    }
}
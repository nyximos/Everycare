package wd.team4.everycare.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.domain.Test;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class TestApiController {

    @ResponseBody
    @GetMapping("/test")
    public Test test(){
        System.out.println("test");
        Test test = new Test();
        test.setId("1");
        test.setName("test");
        return test;
    }

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

    /*
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


    /*
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

}
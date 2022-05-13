package wd.team4.everycare.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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

}
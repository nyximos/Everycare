package wd.team4.everycare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.CareSitterDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.CareSitterServiceImpl;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class CareSitterController {
    
    @Autowired
    CareSitterServiceImpl careSitterService;

    @ResponseBody
    @PostMapping("/dashboard/caresitter")
    public ResponseEntity<MyResponse> postCareSitter(
            @RequestBody CareSitterDTO careSitterDTO
    ){
        LocalDateTime time = LocalDateTime.now();
        careSitterDTO.createTime(time);
        careSitterDTO.updateTime(time);

        Long careSitterId = careSitterService.register(careSitterDTO);

        MyResponse<CareSitterDTO> body = MyResponse.<CareSitterDTO>builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .body(careSitterDTO)
                .build();


        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

}

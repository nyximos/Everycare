package wd.team4.everycare.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.CareSitterFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.CareSitterServiceImpl;
import wd.team4.everycare.service.FileStoreService;

import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api")
public class CareSitterApiController {

    private final FileStoreService fileStoreService;
    private final CareSitterServiceImpl careSitterService;

    public CareSitterApiController(FileStoreService fileStoreService, CareSitterServiceImpl careSitterService) {
        this.fileStoreService = fileStoreService;
        this.careSitterService = careSitterService;
    }

    @ResponseBody
    @PostMapping("/dashboard/caresitter")
    public ResponseEntity<MyResponse> saveCareSitter(
            @ModelAttribute CareSitterFormDTO careSitterFormDTO
    ) throws IOException {

        LocalDateTime time = LocalDateTime.now();
        careSitterFormDTO.createTime(time);
        careSitterFormDTO.updateTime(time);

        careSitterService.save(careSitterFormDTO);

        MyResponse<CareSitterFormDTO> body = MyResponse.<CareSitterFormDTO>builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .body(careSitterFormDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    /*
    @ResponseBody
    @PutMapping("/dashboard/caresitter")
    public ResponseEntity<MyResponse> putCareSitter(
            @RequestBody CareSitterDTO careSitterDTO
    ){
        LocalDateTime time = LocalDateTime.now();
        careSitterService.
    }

     */

    /*
    @ResponseBody
    @DeleteMapping("/dashboard/caresitter/{id}")
    public ResponseEntity<MyResponse> deleteCareSitter(@PathVariable("id") Long id){
        careSitterService.removeCareSitter(id);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
     */
}
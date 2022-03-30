package wd.team4.everycare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.CareTargetServiceImpl;

@RestController
@RequestMapping("/api")
public class CareTargetApiController {

    @Autowired
    CareTargetServiceImpl careTargetService;

    @ResponseBody
    @PostMapping("/carenote/caretargets/new")
    public ResponseEntity<MyResponse> saveCareTarget(
            @RequestBody CareTargetDTO careTargetDTO
    ){
        careTargetService.save(careTargetDTO);

        MyResponse<CareTargetDTO> body = MyResponse.<CareTargetDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careTargetDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}
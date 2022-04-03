package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.CareTargetFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.CareTargetServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareTargetApiController {

    private final CareTargetServiceImpl careTargetService;

    @ResponseBody
    @PostMapping("/carenote/caretargets/new")
    public ResponseEntity<MyResponse> saveCareTarget(
            @ModelAttribute CareTargetFormDTO careTargetFormDTO
    ) throws IOException {
        careTargetService.save(careTargetFormDTO);

        MyResponse<CareTargetFormDTO> body = MyResponse.<CareTargetFormDTO>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(careTargetFormDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}

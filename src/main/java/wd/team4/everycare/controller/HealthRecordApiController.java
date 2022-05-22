package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.careRecord.HealthRecordFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.HealthRecordServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HealthRecordApiController {

    private final HealthRecordServiceImpl healthRecordService;

    @PostMapping("/api/carenote/{id}/health-records")
    public ResponseEntity<MyResponse> save(@PathVariable("id") Long id, @ModelAttribute HealthRecordFormDTO healthRecordFormDTO) {
        ResponseEntity<MyResponse> responseEntity = healthRecordService.save(id, healthRecordFormDTO);
        return responseEntity;
    }
}

package wd.team4.everycare.controller.api;

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

    @GetMapping("/carenote/{id}/health-records")
    public ResponseEntity<MyResponse> getAll(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = healthRecordService.getAll(id);
        return responseEntity;
    }

    @GetMapping("/carenote/{carenoteId}/health-records/{id}")
    public ResponseEntity<MyResponse> get(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = healthRecordService.get(id);
        return responseEntity;
    }

    @PostMapping("/carenote/{id}/health-records")
    public ResponseEntity<MyResponse> save(@PathVariable("id") Long id, @ModelAttribute HealthRecordFormDTO healthRecordFormDTO) {
        ResponseEntity<MyResponse> responseEntity = healthRecordService.save(id, healthRecordFormDTO);
        return responseEntity;
    }

    @PatchMapping("/carenote/{carenoteId}/health-records/{id}")
    public ResponseEntity<MyResponse> update(@PathVariable("id") Long id, @ModelAttribute HealthRecordFormDTO healthRecordFormDTO) {
        ResponseEntity<MyResponse> responseEntity = healthRecordService.update(id, healthRecordFormDTO);
        return responseEntity;
    }

    @DeleteMapping("/carenote/{carenoteId}/health-records/{id}")
    public ResponseEntity<MyResponse> remove(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = healthRecordService.remove(id);
        return responseEntity;
    }
}
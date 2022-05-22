package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.dto.HealthClassificationDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.HealthClassificationServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HealthClassificationApiController {

    private final HealthClassificationServiceImpl healthClassificationService;

    @GetMapping("/health-categories")
    public ResponseEntity<MyResponse> getAll() {
        ResponseEntity<MyResponse> responseEntity = healthClassificationService.getAll();
        return responseEntity;
    }

    @GetMapping("/health-categories/{id}")
    public ResponseEntity<MyResponse> get(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = healthClassificationService.get(id);
        return responseEntity;
    }

    @PostMapping("/health-categories")
    public ResponseEntity<MyResponse> save(@RequestParam("name") String name) {
        ResponseEntity<MyResponse> responseEntity = healthClassificationService.save(name);
        return responseEntity;
    }

    @PostMapping("/health-categories/{id}")
    public ResponseEntity<MyResponse> save(@PathVariable("id") Long id, @RequestParam("name") String name) {
        ResponseEntity<MyResponse> responseEntity = healthClassificationService.update(id, name);
        return responseEntity;
    }

    @GetMapping("/health-categories/{id}")
    public ResponseEntity<MyResponse> remove(@PathVariable("id") Long id) {
        ResponseEntity<MyResponse> responseEntity = healthClassificationService.remove(id);
        return responseEntity;
    }

}

package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}

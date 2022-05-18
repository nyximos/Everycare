package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.ActivityClassificationServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActivityClassificationApiController {

    private final ActivityClassificationServiceImpl activityClassificationService;

    @GetMapping("/activity-categories")
    public ResponseEntity<MyResponse> getAll() {
        ResponseEntity<MyResponse> responseEntity = activityClassificationService.getAll();
        return responseEntity;
    }
}

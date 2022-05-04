package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.JobSearchServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobSearchApiController {

    private final JobSearchServiceImpl jobSearchService;

    @GetMapping("/jobsearchs")
    public ResponseEntity<MyResponse> findJobSearch(Model model) {
        List<CareSitter> allJobSearch = jobSearchService.findAllJobSearch();

        MyResponse<Object> body = MyResponse.builder()
                .header(StatusEnum.OK)
                .body(allJobSearch)
                .message("ok").
                build();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @GetMapping("/jobsearchs/jobsearch/{id}")
    public ResponseEntity<MyResponse> getDetailJobSearch(@PathVariable("id") String id, Model model){

        CareSitter detailJobSearch = jobSearchService.findDetailJobSearch(id);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .body(detailJobSearch)
                .message("ok")
                .build();

        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}
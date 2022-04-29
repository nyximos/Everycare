package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.service.JobSearchServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobSearchWebController {

    private final JobSearchServiceImpl jobSearchService;

//    @GetMapping("/jobsearchs")
//    public MyResponse<Object> findJobSearch() {
//        MyResponse<Object> allJobSearch = jobSearchService.findAllJobSearch();
//
//        MyResponse.builder()
//                .header(StatusEnum.OK)
//                .body(allJobSearch)
//                .message("ㅅㄱ").build();
//        return allJobSearch;
//    }

    @GetMapping("/jobsearchs")
    public String findJobSearch(Model model) {
        List<CareSitter> allJobSearch = jobSearchService.findAllJobSearch();

        System.out.println("allJobSearch = " + allJobSearch);
        MyResponse.builder()
                .header(StatusEnum.OK)
                .body(allJobSearch)
                .message("ㅅㄱ").build();

        model.addAttribute("search", allJobSearch);
        return "jobsearchs";
    }

    @GetMapping("/jobserachs/jobsearch")
    public String findDetailJobSearch(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                      Model model){
        String username = principalDetails.getUsername();
        CareSitter detailJobSearch = jobSearchService.findDetailJobSearch(username);

        MyResponse.builder()
                .header(StatusEnum.OK)
                .body(detailJobSearch)
                .message("ok").build();

        model.addAttribute("detail", detailJobSearch);
        return "jobserach-view";
    }
}

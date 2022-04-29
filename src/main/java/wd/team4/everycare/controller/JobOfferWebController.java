package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.response.MyListResponse;
import wd.team4.everycare.dto.response.MyOptionalResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.service.JobOfferServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class JobOfferWebController {

    private final JobOfferRepository jobOfferRepository;
    private final JobOfferServiceImpl jobOfferService;
    private final CareTargetRepository careTargetRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;

   @GetMapping("/recruitions")
    public ResponseEntity<MyListResponse> getJobOffer(Model model) {

        List<JobOffer> jobOffers = jobOfferService.getJobOffer();

        model.addAttribute("jobOffers",jobOffers);
        MyListResponse<JobOffer> body = MyListResponse.<JobOffer>builder()
                .header(StatusEnum.OK)
                .message("조회 성공")
                .body(jobOffers)
                .build();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyListResponse>(body, headers, HttpStatus.OK);
    }

    @GetMapping("/recruitions/recruition/{id}")
    public ResponseEntity<MyOptionalResponse> getDetailJobOffer(@PathVariable("id") Long id) {

        Optional<JobOffer> findJobOffer = jobOfferService.getDetailJobOffer(id);
        if (findJobOffer.isPresent()) {
            MyOptionalResponse<JobOffer> body = MyOptionalResponse.<JobOffer>builder()
                    .header(StatusEnum.OK)
                    .message("상세 조회 성공")
                    .body(findJobOffer)
                    .build();
            HttpHeaders headers = new HttpHeaders();

            return new ResponseEntity<MyOptionalResponse>(body, headers, HttpStatus.OK);
        } else {
            MyOptionalResponse<JobOffer> body = MyOptionalResponse.<JobOffer>builder()
                    .header(StatusEnum.NOT_FOUND)
                    .message("상세 조회 실패! 구인글이 없습니다.")
                    .body(null)
                    .build();
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<MyOptionalResponse>(body, headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recruitions/new")
    public ResponseEntity<MyListResponse> newJobOffer(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        Member user = principalDetails.getUser();
        System.out.println("user = " + user);

        List<CareTarget> findCareTarget = careTargetRepository.findAllByMember_Id(user.getId());
        MyListResponse<CareTarget> body = MyListResponse.<CareTarget>builder()
                .header(StatusEnum.OK)
                .message("조회 성공")
                .body(findCareTarget)
                .build();
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<MyListResponse>(body, headers, HttpStatus.OK);
    }


//    @GetMapping("/recruitions")
//    public String getJobOffer(Model model) {
//
//        List<JobOffer> jobOffers = jobOfferService.getJobOffer();
//        model.addAttribute("jobOffers",jobOffers);
//        return "joboffers";
//    }
//
//
//
//    @GetMapping("/recruitions/recruition/{id}")
//    public String getDetailJobOffer(@PathVariable("id") Long id, Model model) {
//
//        Optional<JobOffer> findJobOffer = jobOfferService.getDetailJobOffer(id);
//        if(findJobOffer.isPresent()){
//            model.addAttribute("find", findJobOffer.get());
//        }else{
//            return "없음";
//        }
//        return "joboffer-view";
//    }
//
//    @GetMapping("/recruitions/new")
//    public String newJobOffer(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
//
//        Member user = principalDetails.getUser();
//
//        List<CareTarget> findCareTarget = careTargetRepository.findAllByMember_Id(user.getId());
//        model.addAttribute("careTarget", findCareTarget);
//
//        return "joboffer-new";
//    }

}

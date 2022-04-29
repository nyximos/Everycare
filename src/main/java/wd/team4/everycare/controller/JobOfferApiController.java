package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;
import wd.team4.everycare.dto.response.MyListResponse;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.service.JobOfferServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JobOfferApiController {

    private final JobOfferServiceImpl jobOfferService;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
    private final CareTargetRepository careTargetRepository;


//    @PostMapping("/recruitions/recruition/{id}")
//    public List<CareTargetSchedule> putCareTarget(@PathVariable Long id) {
////        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
//        List<CareTargetSchedule> findSchedule = careTargetScheduleRepository.findByCareTarget_Id(id);
//        System.out.println(findSchedule);
//        return findSchedule;
//    }

    @GetMapping("/recruitions/recruition/{id}")
    public ResponseEntity<MyResponse> deleteJobOffer(@PathVariable("id") Long id) {
        jobOfferService.deleteJobOffer(id);
        MyResponse<JobOffer> body = MyResponse.<JobOffer>builder()
                .header(StatusEnum.OK)
                .message("삭제 성공 ")
                .body(null)
                .build();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @PostMapping("/recruitions/schedules")
    public ResponseEntity<MyListResponse> regCareTargetSchedule(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                                Long id) {
        System.out.println("careTargetId = " + id);
        List<CareTargetSchedule> findSchedule = careTargetScheduleRepository.findByCareTarget_Id(id);

        MyListResponse<CareTargetSchedule> body = MyListResponse.<CareTargetSchedule>builder()
                .header(StatusEnum.OK)
                .message("스케줄 조회 성공")
                .body(findSchedule)
                .build();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyListResponse>(body, headers, HttpStatus.OK);
    }

    @PostMapping("/recruitions/recruition")
    public ResponseEntity<MyResponse> saveJobOffer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                   @ModelAttribute JobOfferDTO jobOfferDTO){
        Member username = principalDetails.getUser();
        jobOfferDTO.setMember(username);

        jobOfferService.save(jobOfferDTO);

        MyResponse<JobOfferDTO> body = MyResponse.<JobOfferDTO>builder()
                .header(StatusEnum.OK)
                .message("구인글 등록 성공")
                .body(jobOfferDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

//    @PatchMapping("/recruitions/recruition")
//    public ResponseEntity<MyResponse> updateJobOffer(@AuthenticationPrincipal PrincipalDetails principalDetails,
//                                                     @ModelAttribute JobOfferDTO jobOfferDTO) {
//
//    }
}
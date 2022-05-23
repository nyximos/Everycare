package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobOfferDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobOfferDTO;
import wd.team4.everycare.dto.response.MyOptionalResponse;
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
//성공
    @DeleteMapping("/recruitions/recruition/{id}")
    public ResponseEntity<MyResponse> deleteJobOffer(@PathVariable("id") Long id) {
        jobOfferService.deleteJobOffer(id);
        MyResponse<JobOffer> body = MyResponse.<JobOffer>builder()
                .header(StatusEnum.OK)
                .message("삭제 성공 ")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
//성공
    @PostMapping("/recruitions/schedules")
    public ResponseEntity<MyResponse> regCareTargetSchedule(Long id) {
        System.out.println("id = " + id);
        List<CareTargetScheduleListDTO> schedule = jobOfferService.findSchedule(id);
        System.out.println("schedule = " + schedule);
        MyResponse<Object> body = MyResponse.<Object>builder()
                .header(StatusEnum.OK)
                .message("스케줄 조회 성공")
                .body(schedule)
                .build();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @PostMapping("/recruitions/recruition")
    public ResponseEntity<MyResponse> saveJobOffer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                   @RequestBody JobOfferDTO jobOfferDTO){

        jobOfferService.save(principalDetails, jobOfferDTO);
        /* TODO 케어대상인,스케줄 type mismatch 해결했는지 확인*/

        MyResponse<JobOfferDTO> body = MyResponse.<JobOfferDTO>builder()
                .header(StatusEnum.OK)
                .message("구인글 등록 성공")
                .body(jobOfferDTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }

    @PatchMapping("/recruitions/recruition/{id}")
    public ResponseEntity<MyResponse> updateJobOffer(@PathVariable("id") Long id,
                                                     @ModelAttribute JobOfferDTO jobOfferDTO) {
        jobOfferService.update(id, jobOfferDTO);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
//getJobOffer완료
    @GetMapping("/recruitions")
    public ResponseEntity<MyResponse> getJobOffer() {

        List<JobOfferDTO> jobOffers = jobOfferService.getJobOffer();

        MyResponse<Object> body = MyResponse.<Object>builder()
                .header(StatusEnum.OK)
                .message("조회 성공")
                .body(jobOffers)
                .build();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
//detail 완료
    @GetMapping("/recruitions/recruition/{id}")
    public ResponseEntity<Object> getDetailJobOffer(@PathVariable("id") Long id) {

        DetailJobOfferDTO detailJobOffer = jobOfferService.getDetailJobOffer(id);
        System.out.println("detailJobOffer = " + detailJobOffer);
        if (detailJobOffer!=null) {
            MyResponse<Object> body = MyResponse.<Object>builder()
                    .header(StatusEnum.OK)
                    .message("상세 조회 성공")
                    .body(detailJobOffer)
                    .build();
            HttpHeaders headers = new HttpHeaders();

            return new ResponseEntity<Object>(body, headers, HttpStatus.OK);
        } else {
            MyOptionalResponse<JobOffer> body = MyOptionalResponse.<JobOffer>builder()
                    .header(StatusEnum.NOT_FOUND)
                    .message("상세 조회 실패! 구인글이 없습니다.")
                    .body(null)
                    .build();
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Object>(body, headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recruitions/new")
    public ResponseEntity<MyResponse> newJobOffer(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        String userId = principalDetails.getUser().getId();
        System.out.println("userId = " + userId);
        List<CareTargetFormDTO> findCareTarget = jobOfferService.findCareTarget(userId);
        MyResponse<Object> body = MyResponse.<Object>builder()
                .header(StatusEnum.OK)
                .message("조회 성공")
                .body(findCareTarget)
                .build();
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}

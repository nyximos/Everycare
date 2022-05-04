package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;
import wd.team4.everycare.dto.response.MyListResponse;
import wd.team4.everycare.dto.response.MyOptionalResponse;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.service.JobOfferServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JobOfferApiController {

    private final JobOfferServiceImpl jobOfferService;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
    private final CareTargetRepository careTargetRepository;

    @DeleteMapping("/recruitions/recruition/{id}")
    public ResponseEntity<MyResponse> deleteJobOffer(@PathVariable("id") Long id) {
        jobOfferService.deleteJobOffer(id);
        MyResponse<JobOffer> body = MyResponse.<JobOffer>builder()
                .header(StatusEnum.OK)
                .message("삭제 성공 ")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @PostMapping("/recruitions/schedules")
    public ResponseEntity<MyListResponse> regCareTargetSchedule(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                                Long id) {
        System.out.println("careTargetId = " + id);
        List<CareTargetSchedule> findSchedule = careTargetScheduleRepository.findByCareTargetId(id);

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

    @PatchMapping("/recruitions/recruition{id}")
    public ResponseEntity<MyResponse> updateJobOffer(@PathVariable("id") Long id,
                                                     @ModelAttribute JobOfferDTO jobOfferDTO) {
        jobOfferService.update(id, jobOfferDTO);
        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    @GetMapping("/recruitions")
    public ResponseEntity<MyListResponse> getJobOffer() {

        List<JobOffer> jobOffers = jobOfferService.getJobOffer();

        MyListResponse<JobOffer> body = MyListResponse.<JobOffer>builder()
                .header(StatusEnum.OK)
                .message("조회 성공")
                .body(jobOffers)
                .build();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyListResponse>(body, headers, HttpStatus.OK);
    }

    @GetMapping("/recruitions/recruition/{id}")
    public ResponseEntity<Object> getDetailJobOffer(@PathVariable("id") Long id) {

        JobOfferDTO detailJobOffer = jobOfferService.getDetailJobOffer(id);
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
}

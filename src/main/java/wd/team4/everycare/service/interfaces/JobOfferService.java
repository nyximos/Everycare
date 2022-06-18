package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobOfferDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.time.LocalDate;
import java.util.List;

public interface JobOfferService {

    List<JobOfferDTO> getJobOffer();
    ResponseEntity<MyResponse> getDetailJobOffer(Long id);
    List<CareTargetScheduleListDTO> findSchedule(Long id);
    List<CareTargetFormDTO> findCareTarget(String id);
    JobOffer save(PrincipalDetails principalDetails, JobOfferDTO jobOfferDTO);
    String update(Long id, JobOfferDTO jobOfferDTO);
    void deleteJobOffer(Long id);
    ResponseEntity<MyResponse> offer(Long jobOfferId, PrincipalDetails principalDetails);
    ResponseEntity<MyResponse> findOffer(Long jobOfferId, PrincipalDetails principalDetails);
    ResponseEntity<MyResponse> findDetailOffer(Long contractId);
    ResponseEntity<MyResponse> searchPay(int min, int max);
    ResponseEntity<MyResponse> searchRegion(String region);
    ResponseEntity<MyResponse> searchDate(LocalDate date);

    ResponseEntity<MyResponse> announceOffer(PrincipalDetails principalDetails);
}
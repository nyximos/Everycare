package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobOfferDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobOfferDTO;

import java.util.List;

public interface JobOfferService {

    List<JobOfferDTO> getJobOffer();
    DetailJobOfferDTO getDetailJobOffer(Long id);
    List<CareTargetSchedule> findSchedule(Long id);
    List<CareTargetFormDTO> findCareTarget(String id);
    JobOffer save(JobOfferDTO jobOfferDTO);
    String update(Long id, JobOfferDTO jobOfferDTO);
    void deleteJobOffer(Long id);
    JobOffer offer(Long id, JobOfferDTO jobOfferDTO);



}
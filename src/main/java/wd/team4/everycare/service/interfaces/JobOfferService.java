package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.JobOfferDTO;

import java.util.List;
import java.util.Optional;

public interface JobOfferService {

    List<JobOffer> getJobOffer();
    JobOfferDTO getDetailJobOffer(Long id);
    JobOffer save(JobOfferDTO jobOfferDTO);
    String update(Long id, JobOfferDTO jobOfferDTO);
    void deleteJobOffer(Long id);
    JobOffer offer(Long id, JobOfferDTO jobOfferDTO);

    default JobOffer dtoToEntity(JobOfferDTO dto) {
        JobOffer jobOffer = JobOffer.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .desiredDayWeek(dto.getDesiredDayWeek())
                .desiredStartTime(dto.getDesiredStartTime())
                .desiredEndTime(dto.getDesiredEndTime())
                .pay(dto.getPay())
                .desiredCareSitterGender(dto.getDesiredCareSitterGender())
                .comment(dto.getComment())
                .build();
        return jobOffer;
    }


}
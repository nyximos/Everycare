package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.CareTargetScheduleDTO;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface JobOfferService {

    List<JobOffer> getJobOffer();
    Optional<JobOffer> getDetailJobOffer(Long id);
    JobOffer modifyJobOffer(Long id, JobOfferDTO jobOfferDTO);
    JobOffer save(Long id, CareTargetDTO careTargetDTO, CareTargetScheduleDTO scheduleDTO);
    void deleteJobOffer(Long id);

    default JobOffer dtoToEntity(JobOfferDTO dto) {
        JobOffer jobOffer = JobOffer.builder()
                .id(dto.getId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .desiredDayWeek(dto.getDesiredDayWeek())
                .desiredStartTime(dto.getDesiredStartTime())
                .desiredActivityTime(dto.getDesiredActivityTime())
                .pay(dto.getPay())
                .desiredCareSitterGender(dto.getDesiredCareSitterGender())
                .comment(dto.getComment())
                .build();
        return jobOffer;
    }

    default List<JobOffer> ListDtoToListEntity(List<JobOfferDTO> jobOffers){
        return jobOffers.stream()
                .map(JobOfferDTO::toListEntity)
                .collect(Collectors.toList());
    }


}
package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.CareTargetScheduleDTO;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.service.interfaces.JobOfferService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final CareTargetRepository careTargetRepository;

    @Override
    @Transactional(readOnly = true)
    public List<JobOffer> getJobOffer() {
        List<JobOffer> allList = jobOfferRepository.findAll();
        return allList;
    }

    @Override
    public Optional<JobOffer> getDetailJobOffer(Long id) {
        Optional<JobOffer> findJobOffer = jobOfferRepository.findById(id);
            return findJobOffer;
    }

    @Override
    public JobOffer save(PrincipalDetails principalDetails, CareTargetDTO careTargetDTO, CareTargetScheduleDTO scheduleDTO) {
        JobOfferDTO jobOfferDTO = new JobOfferDTO();
//        jobOfferDTO.setStartDate(scheduleDTO.getStartTime());
    }

    @Override
    public void deleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }

//    @Override
//    public JobOffer modifyJobOffer(Long id, JobOfferDTO jobOfferDTO) {
//        Optional<JobOffer> findJobOffer = jobOfferRepository.findById(id);
//        return null;
//    }

//    @Override
//    public JobOffer save(Long id, CareTargetDTO careTargetDTO, CareTargetScheduleDTO scheduleDTO) {
//        return null;
//    }

}

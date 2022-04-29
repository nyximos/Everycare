package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.JobOfferDTO;
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

    @Override
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
    public JobOffer save(JobOfferDTO jobOfferDTO) {
        JobOffer jobOffer = jobOfferDTO.toJobOffer();
        JobOffer saveJobOffer = jobOfferRepository.save(jobOffer);
        return saveJobOffer;
    }

//    @Override
//    public JobOffer update(JobOfferDTO jobOfferDTO) {
//
//    }

    @Override
    public void deleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }


}

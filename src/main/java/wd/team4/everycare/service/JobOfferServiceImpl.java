package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.careTargetSchedule.CareTargetScheduleListDTO;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;
import wd.team4.everycare.dto.caretarget.CareTargetFormDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.DetailJobOfferDTO;
import wd.team4.everycare.dto.jobOffer_jobSearch.JobOfferDTO;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.repository.ContractRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.service.interfaces.JobOfferService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
    private final CareTargetRepository careTargetRepository;
    private final ContractRepository contractRepository;

    @Override
    public List<JobOfferDTO> getJobOffer() {
        List<JobOffer> allList = jobOfferRepository.findAll();
        List<JobOfferDTO> jobOfferDTOs = new ArrayList<>();
        allList.stream().map(jobOffer -> jobOffer.toJobOfferDTO()).forEach(jobOfferDTOs::add);
        return jobOfferDTOs;
    }

    @Override
    public DetailJobOfferDTO getDetailJobOffer(Long id) {
        JobOffer findJobOffer = jobOfferRepository.findById(id).get();
        DetailJobOfferDTO jobOfferDTO = findJobOffer.toDetailJobOfferDTO(findJobOffer);
        return jobOfferDTO;
    }

    @Override
    public List<CareTargetScheduleListDTO> findSchedule(Long id) {
        List<CareTargetSchedule> findCareTargetSchedule = careTargetScheduleRepository.findAllByCareTargetId(id);
        List<CareTargetScheduleListDTO> careTargetScheduleListDTOs = new ArrayList<>();
        findCareTargetSchedule.stream().map(careTargetSchedule -> careTargetSchedule.toListDTO()).forEach(careTargetScheduleListDTOs::add);
        return careTargetScheduleListDTOs;
    }

    @Override
    public List<CareTargetFormDTO> findCareTarget(String id) {
        List<CareTarget> findCareTarget = careTargetRepository.findAllByMemberId(id);
        List<CareTargetFormDTO> careTargetDTOs = new ArrayList<>();
        findCareTarget.stream().map(careTarget -> careTarget.toFormDTO()).forEach(careTargetDTOs::add);
        System.out.println("findCareTarget = " + findCareTarget);
        return careTargetDTOs;
    }

    @Override
    public JobOffer save(JobOfferDTO jobOfferDTO) {
        JobOffer jobOffer = jobOfferDTO.toJobOffer();
        JobOffer saveJobOffer = jobOfferRepository.save(jobOffer);
        return saveJobOffer;
    }

    @Override
    public String update(Long id, JobOfferDTO jobOfferDTO) {
        Optional<JobOffer> jobOffer = jobOfferRepository.findById(id);
        JobOffer jobOfferEntity = jobOffer.orElse(null);
        System.out.println("jobOfferEntity = " + jobOfferEntity.getTitle());
        if(jobOfferEntity == null) {
            System.out.println("구인글 없음");
            return "실패";
        }else {
            jobOfferEntity.updateInfo(jobOfferDTO);
            return "완료";
        }
    }

    @Override
    public void deleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }

    @Override
    public JobOffer offer(Long id, JobOfferDTO jobOfferDTO) {
        Optional<JobOffer> jobOffer = jobOfferRepository.findById(id);
        /*TODO 회원이 구인글에서 요청온거 보는거*/
        return null;
    }


}

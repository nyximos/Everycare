package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.repository.ContractRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.service.interfaces.JobOfferService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final ContractRepository contractRepository;

    @Override
    public List<JobOffer> getJobOffer() {
        List<JobOffer> allList = jobOfferRepository.findAll();
        return allList;
    }

    @Override
    public JobOfferDTO getDetailJobOffer(Long id) {
        JobOffer findJobOffer = jobOfferRepository.findById(id).get();
        Member member = findJobOffer.getMember();
        System.out.println("member = " + member);
        CareTargetSchedule careTargetSchedule = findJobOffer.getCareTargetSchedule();
        System.out.println("careTargetSchedule = " + careTargetSchedule);
        CareTarget careTarget = findJobOffer.getCareTarget();
        System.out.println("careTarget = " + careTarget);
        JobOfferDTO jobOfferDTO = findJobOffer.toJobOfferDTO(findJobOffer);
        // 내 생각에 LAZY라서 proxy 객체가 이전에 조회 한 적 없어서 null로 뜨는듯 careTarget 찾는다고 하니까 null 안뜸
        return jobOfferDTO;
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

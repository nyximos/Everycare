package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.service.interfaces.JobSearchService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobSearchServiceImpl implements JobSearchService {

    private final CareSitterRepository careSitterRepository;

    @Override
    public List<CareSitter> findAllJobSearch() {
        List<CareSitter> allByDisclosureStatus = careSitterRepository.findAllByDisclosureStatus(1);             //1이나 0이 공개일텐데 그거 따라 숫자 바꿔주기
        return allByDisclosureStatus;
    }

    @Override
    public CareSitter findJobSearch() {
        return null;
    }

    @Override
    public CareSitter findDetailJobSearch(String memberId) {
        Optional<CareSitter> findJobSearch = careSitterRepository.findByMember_Id(memberId);
        return findJobSearch.get();
    }
}

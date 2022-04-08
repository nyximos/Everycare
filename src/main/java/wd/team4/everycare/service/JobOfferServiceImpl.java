package wd.team4.everycare.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wd.team4.everycare.config.jwt.JwtProperties;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.CareTargetScheduleDTO;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.dto.caretarget.CareTargetDTO;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.service.interfaces.JobOfferService;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final CareTargetRepository careTargetRepository;

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
    public JobOffer modifyJobOffer(Long id, JobOfferDTO jobOfferDTO) {
        Optional<JobOffer> findJobOffer = jobOfferRepository.findById(id);
        return null;
    }

    @Override
    public JobOffer save(Long id, CareTargetDTO careTargetDTO, CareTargetScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public void deleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }
}

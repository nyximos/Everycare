package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetSchedule;
import wd.team4.everycare.domain.Result;
import wd.team4.everycare.dto.CareTargetAndScheduleDTO;
import wd.team4.everycare.dto.CareTargetScheduleDTO;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.service.JobOfferServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JobOfferApiController {

    private final JobOfferServiceImpl jobOfferService;
    private final CareTargetScheduleRepository careTargetScheduleRepository;
    private final CareTargetRepository careTargetRepository;


    @PostMapping("/recruitions/recruition/{id}")
    public List<CareTargetSchedule> putCareTarget(@PathVariable Long id) {
//        Optional<CareTarget> careTarget = careTargetRepository.findById(id);
        List<CareTargetSchedule> findSchedule = careTargetScheduleRepository.findByCareTarget_Id(id);
        System.out.println(findSchedule);
        return findSchedule;
    }

    @PostMapping("/recruitions/recruitions/{id}")
    public Result deleteJobOffer(@PathVariable("id") Long id) {
        jobOfferService.deleteJobOffer(id);
        return new Result("ok");
    }

    @PostMapping("/recruitions/new")
    public JobOfferDTO regJobOffer(CareTargetAndScheduleDTO dto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("들오냐");
        return null;
    }
}

 
package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.service.JobOfferServiceImpl;

import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
public class JobOfferApiController {

    private final JobOfferServiceImpl jobOfferService;

    /**
     * TODO 구인 글 등록
     */
    @PutMapping("/recruitions/recruition/{id}")
    public JobOfferDTO putJobOffer(@PathVariable("id") String id, Model model) {

        return null;
    }


    @PostMapping("/recruitions/recruition/{id}")
    public void deleteJobOffer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                               @ModelAttribute JobOfferDTO jobOfferDTO) {

    }
}
 
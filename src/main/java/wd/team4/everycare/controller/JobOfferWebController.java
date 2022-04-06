package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.config.auth.PrincipalDetailsService;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.service.JobOfferServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class JobOfferWebController {

    private final JobOfferRepository jobOfferRepository;
    private final JobOfferServiceImpl jobOfferService;
    private final CareTargetRepository careTargetRepository;
    private final CareTargetScheduleRepository careTargetScheduleRepository;

    @GetMapping("/recruitions")
    public String getJobOffer(Model model){
        List<JobOffer> jobOffers = jobOfferService.getJobOffer();
        model.addAttribute("jobOffers",jobOffers);
        System.out.println(jobOffers.size());
        return "recruitions";
    }

    @GetMapping("/recruitions/recruition/{id}")
    public String getDetailJobOffer(@PathVariable("id") Long id, Model model){
        Optional<JobOffer> detailJobOffer = jobOfferRepository.findById(id);
        if(detailJobOffer.isPresent()){
            //값 있으면 추가
            model.addAttribute("detail", detailJobOffer.get());
        }
        else{
            return null;
        }
        return "recruition_detail";
    }

    @GetMapping("/recruitions/new")
    public String newJobOffer(Model model, PrincipalDetails principalDetails) {

        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        System.out.println(principal);

        System.out.println("principalDetails = " + principalDetails);

        System.out.println("principalDetails = " + principalDetails.getUsername());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        Object principal1 = authentication.getPrincipal();
        System.out.println("principal1 = " + principal1);

        return "recruition_new";
    }
}

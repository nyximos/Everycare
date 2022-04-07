package wd.team4.everycare.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
import wd.team4.everycare.config.jwt.JwtProperties;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.repository.CareTargetScheduleRepository;
import wd.team4.everycare.repository.JobOfferRepository;
import wd.team4.everycare.service.JobOfferServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
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
    public String newJobOffer(Model model, HttpServletRequest request)  {
        String accessToken = request.getHeader("Authorization");
        System.out.println("accessToken = " + accessToken);
        String token = accessToken.replace("Bearer ", "");
//        System.out.println("token = " + token);
//        Claims body = Jwts.parser().setSigningKey(JwtProperties.SECRET.getBytes(StandardCharsets.UTF_8))
//                .parseClaimsJws(token).getBody();
//
//        System.out.println(body.getSubject());
//        System.out.println(body);
//        System.out.println(body.get("username"));

        return "recruition_new";
    }
}

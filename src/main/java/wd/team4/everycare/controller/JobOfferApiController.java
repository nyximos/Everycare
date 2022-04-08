package wd.team4.everycare.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.config.jwt.JwtProperties;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.dto.JobOfferDTO;
import wd.team4.everycare.repository.CareTargetRepository;
import wd.team4.everycare.service.JobOfferServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
public class JobOfferApiController {

    private final JobOfferServiceImpl jobOfferService;

    private final CareTargetRepository careTargetRepository;

    /**
     * TODO 구인 글 등록
     */

    @PutMapping("/recruitions/recruition/{id}")
    public JobOfferDTO putJobOffer(@PathVariable("id") String id, Model model) {

        return null;
    }

    @PostMapping("/recruitions/recruition/new")
    public List<CareTarget> putCareTarget(Model model, HttpServletRequest request) {

        String accessToken = request.getHeader("Authorization");
        if (accessToken != null) {
            System.out.println("accessToken = " + accessToken);

            Claims body = Jwts.parser().setSigningKey(JwtProperties.SECRET.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(accessToken).getBody();

            Object username = body.get("username");

            System.out.println("username = " + username);

            careTargetRepository.findAllByMember_Id(String.valueOf(username));
        } else {
            return null;
        }
        return null;
    }


    @PostMapping("/recruitions/recruition/{id}")
    public void deleteJobOffer(@PathVariable("id") Long id) {
        jobOfferService.deleteJobOffer(id);
    }

}

 
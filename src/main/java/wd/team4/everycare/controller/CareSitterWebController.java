package wd.team4.everycare.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import wd.team4.everycare.config.jwt.JwtProperties;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.service.FileStoreService;
import wd.team4.everycare.service.interfaces.CareSitterService;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CareSitterWebController {

    private final CareSitterService careSitterService;
    private final CareSitterRepository careSitterRepository;
    private final FileStoreService fileStoreService;

    @GetMapping("/dashboard/caresitter")
    public String newCareSitter(HttpServletRequest request) {


        return "caresitter-new";
    }

//    @GetMapping("/dashboard/caresitter")
//    public String newCareSitter(@RequestHeader String authorization) {
//
//        String loginId = memberService.getId(authorization);
//        System.out.println("loginId = " + loginId);
//        return "caresitter-new";
//    }

    /*
    @GetMapping("/dashboard/caresitter")
    public String newCareSitter(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        Object principal = SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal();
//        System.out.println(principalDetails.getUser());

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("principal : " + authentication.getPrincipal());
//        System.out.println("Implementing class of UserDetails: " + authentication.getPrincipal().getClass());
//        System.out.println("Implementing class of UserDetailsService: " + principalDetails.getClass());
//        System.out.println(principalDetails.getUsername());
        return "caresitter-new";
    }
    */


    @GetMapping("/caresitters/{id}")
    public String careSitterDetail(@PathVariable Long id, Model model) {
        Optional<CareSitter> careSitter = careSitterRepository.findById(id);
        if(careSitter.isEmpty()) return null;

        List<CareSitterImage> careSitterImages = careSitterService.findCareSitterImages(id);

        model.addAttribute("careSitter", careSitter.get());
        model.addAttribute("careSitterImages", careSitterImages);
        return "caresitter-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStoreService.getFullPath(filename));
    }
}

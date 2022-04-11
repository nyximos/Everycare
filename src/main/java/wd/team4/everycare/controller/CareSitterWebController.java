package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.service.CareSitterServiceImpl;
import wd.team4.everycare.service.FileStoreService;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CareSitterWebController {

    private final CareSitterServiceImpl careSitterService;
    private final CareSitterRepository careSitterRepository;
    private final FileStoreService fileStoreService;

    @GetMapping("/dashboard/caresitter")
    public String newCareSitter(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String id = principalDetails.getUsername();
        CareSitter careSitter = careSitterService.findCareSitter(id);
        System.out.println("careSitter = " + careSitter);

        if(careSitter!=null){
            model.addAttribute("careSitter", careSitter);
            List<CareSitterImage> careSitterImages = careSitterService.findCareSitterImages(careSitter.getId());
            if(careSitterImages!=null){
                model.addAttribute("careSitterImages", careSitterImages);
            }
        }
        return "caresitter";
    }

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

    @GetMapping("/dashboard/caresitter/{id}")
    public String updateCareSitter(@PathVariable Long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        CareSitter careSitter = careSitterService.isPresent(id);
        List<CareSitterImage> careSitterImages = careSitterService.findCareSitterImages(id);

        model.addAttribute("careSitter", careSitter);
        model.addAttribute("careSitterImages", careSitterImages);

        model.addAttribute(careSitter);

        return "caresitter-update";
    }
}

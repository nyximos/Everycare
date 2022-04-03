package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.CareSitterImage;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.service.FileStoreService;
import wd.team4.everycare.service.interfaces.CareSitterService;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CareSitterWebController {

    private final CareSitterService careSitterService;
    private final CareSitterRepository careSitterRepository;
    private final FileStoreService fileStoreService;

    @GetMapping("/dashboard/caresitter")
    public String newCareSitter() {
        return "caresitter-new";
    }

    @GetMapping("/caresitters/{id}")
    public String careSitters(@PathVariable Long id, Model model) {
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

package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.repository.CareSitterRepository;
import wd.team4.everycare.service.interfaces.CareSitterService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CareSitterWebController {

    private final CareSitterService careSitterService;
    private final CareSitterRepository careSitterRepository;

    @GetMapping("/dashboard/caresitter")
    public String saveCareSitter() {
        return "upload-form2";
    }

    @GetMapping("/caresitters/{id}")
    public String careSitters(@PathVariable Long id, Model model){
        Optional<CareSitter> careSitter = careSitterRepository.findById(id);
        if(careSitter.isEmpty()) return null;

        model.addAttribute("careSitter", careSitter);
        return "caresitter-view";
    }
}

package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.repository.StoreRepository;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StoreWebController {

    private final StoreRepository storeRepository;

    @GetMapping("/store")
    public String store(){
        return "store";
    }

    @GetMapping("/store/new")
    public String newStore(){
        return "store-new";
    }

    @GetMapping("/store/{id}")
    public String storeDetail(@PathVariable Long id, Model model){
        Optional<Store> store = storeRepository.findById(id);
        if(store.isEmpty()) return null;

        model.addAttribute("store", store.get());

        return "store-view";
    }

}

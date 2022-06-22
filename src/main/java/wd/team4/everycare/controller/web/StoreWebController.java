package wd.team4.everycare.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.repository.StoreRepository;
import wd.team4.everycare.service.StoreServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StoreWebController {

    private final StoreServiceImpl storeService;
    private final StoreRepository storeRepository;

    @GetMapping("/store")
    public String store(Model model){
        List<Store> stores = storeRepository.findAll();
        model.addAttribute("stores", stores);
        return "store";
    }

    @GetMapping("/dashboard/store/account")
    public String newStore(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String id = principalDetails.getUsername();
        Store store = storeService.findStoreByMember(id);

        if(store!=null){
            model.addAttribute("store", store);
        }

        return "store-account";
    }

    @GetMapping("dashboard/store/account/update")
    public String updateFormStore(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String id = principalDetails.getUsername();
        Store store = storeService.findStoreByMember(id);
        model.addAttribute("store", store);

        return "store-update";
    }

    @GetMapping("/store/{id}")
    public String storeDetail(@PathVariable Long id, Model model){
        Optional<Store> store = storeRepository.findById(id);
        if(store.isEmpty()) return null;

        model.addAttribute("store", store.get());

        return "store-view";
    }

}

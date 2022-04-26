package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.dto.StoreAdminViewDTO;
import wd.team4.everycare.repository.MemberRepository;
import wd.team4.everycare.service.StoreServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminWebController {

    private final MemberRepository memberRepository;
    private final StoreServiceImpl storeService;

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/admin/members")
    public String memberManagement(Model model){

        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "admin-members";
    }

    @GetMapping("/admin/stores")
    public String adminStores(Model model){
        List<StoreAdminViewDTO> stores = storeService.findStoresThatRequiresApproval();
        model.addAttribute("stores", stores);

        return "admin-stores";
    }

    @GetMapping("/admin/stores/{id}")
    public String adminStore(@PathVariable Long id, Model model){
        StoreAdminViewDTO store = storeService.webFindStore(id);
        model.addAttribute("store", store);

        return "admin-store";
    }

    /* TODO
    @GetMapping("/certifications")
    public String certifications(){
        return "admin-certifications";
    }

    @GetMapping("/reports")
    public String reports(){

        return "admin-reports";
    }

    */

}

package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.product.MemberProductsViewDTO;
import wd.team4.everycare.service.ProductServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductWebController {

    private final ProductServiceImpl productService;

    @GetMapping("/dashboard/store/products")
    public String StoreProducts(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        Store store = principalDetails.getStore();
        List<MemberProductsViewDTO> products = productService.webFindAll(store);
        model.addAttribute("products", products);

        return "member-products";
    }
}

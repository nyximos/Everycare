package wd.team4.everycare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.product.ProductFormDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.ProductServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductApiController {

    private final ProductServiceImpl productService;

    @ResponseBody
    @PostMapping("/dashboard/store/products")
    public ResponseEntity<MyResponse> saveProduct(@AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute ProductFormDTO productFormDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = productService.save(principalDetails, productFormDTO);
        return responseEntity;
    }
}
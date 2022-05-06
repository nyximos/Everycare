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

    @GetMapping("/store/products")
    public ResponseEntity<MyResponse> getProducts(){
        ResponseEntity<MyResponse> responseEntity = productService.findAll();
        return responseEntity;
    }

    @GetMapping("/store/products/{id}")
    public ResponseEntity<MyResponse> getProduct(@PathVariable Long id){
        ResponseEntity<MyResponse> responseEntity = productService.findById(id);
        return responseEntity;
    }

    @PostMapping("/dashboard/store/products")
    public ResponseEntity<MyResponse> saveProduct(@AuthenticationPrincipal PrincipalDetails principalDetails, @ModelAttribute ProductFormDTO productFormDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = productService.save(principalDetails, productFormDTO);
        return responseEntity;
    }

    @GetMapping("/dashboard/store/products")
    public ResponseEntity<MyResponse> getMemberProducts(@AuthenticationPrincipal PrincipalDetails principalDetails){
        ResponseEntity<MyResponse> responseEntity = productService.findAll(principalDetails.getStore());
        return responseEntity;
    }

    @GetMapping("/dashboard/store/products/{id}")
    public ResponseEntity<MyResponse> getMemberProduct(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Long id){
        ResponseEntity<MyResponse> responseEntity = productService.productFindById(principalDetails.getStore(), id);
        return responseEntity;
    }

}

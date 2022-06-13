package wd.team4.everycare.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.dto.MultipartFileDTO;
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
    public ResponseEntity<MyResponse> getMemberProduct(@PathVariable Long id){
        ResponseEntity<MyResponse> responseEntity = productService.productFindById(id);
        return responseEntity;
    }

    @PatchMapping("/dashboard/store/products/{id}")
    public ResponseEntity<MyResponse> updateProduct(@PathVariable Long id, @ModelAttribute ProductFormDTO productFormDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = productService.updateProduct(id, productFormDTO);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/store/products/{id}")
    public ResponseEntity<MyResponse> deleteProduct(@PathVariable Long id) {
        ResponseEntity<MyResponse> responseEntity = productService.deleteProduct(id);
        return responseEntity;
    }

    @PostMapping("/dashboard/store/products/{id}/image")
    public ResponseEntity<MyResponse> saveImage(@PathVariable Long id, @ModelAttribute MultipartFileDTO imageDTO) throws IOException {
        ResponseEntity<MyResponse> responseEntity = productService.saveImage(id,imageDTO);
        return responseEntity;
    }

    @DeleteMapping("/dashboard/store/products/{id}/image/{imageId}")
    public ResponseEntity<MyResponse> removeImage(@PathVariable("imageId") Long id){
        ResponseEntity<MyResponse> responseEntity = productService.removeImage(id);
        return responseEntity;
    }

    @GetMapping("/store/products/name")
    public ResponseEntity<MyResponse> getProducts(@RequestParam("name") String name){
        ResponseEntity<MyResponse> responseEntity = productService.findAllByName(name);
        return responseEntity;
    }

    @GetMapping("/store/products/category")
    public ResponseEntity<MyResponse> getProductsByCategory(@RequestParam("categoryName") String categoryName){
        ResponseEntity<MyResponse> findByCategory = productService.findAllByCategory(categoryName);
        return findByCategory;
    }

    @GetMapping("/store/products/best")
    public ResponseEntity<MyResponse> getBestProducts() {
        ResponseEntity<MyResponse> responseEntity = productService.findBestProducts();
        return responseEntity;
    }

    // TODO 월별 상품
    // TODO 월별 스토어 총 매출

}

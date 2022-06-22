package wd.team4.everycare.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.service.ProductCategoryServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductCategoryApiController {

    private final ProductCategoryServiceImpl productCategoryService;

    @GetMapping("/product-categories")
    public ResponseEntity<MyResponse> getAll() {
        System.out.println("신호받음");
        ResponseEntity<MyResponse> responseEntity = productCategoryService.getAll();
        return responseEntity;
    }
}
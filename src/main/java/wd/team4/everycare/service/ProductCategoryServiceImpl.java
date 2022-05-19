package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ProductCategory;
import wd.team4.everycare.dto.product.ProductCategoryDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.ProductCategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCategoryServiceImpl {

    private final ProductCategoryRepository productCategoryRepository;

    public ResponseEntity<MyResponse> getAll() {

        List<ProductCategory> productCategories = productCategoryRepository.findAll();

        List<ProductCategoryDTO> productCategoryDTOs = new ArrayList<>();

        for (ProductCategory productCategory : productCategories) {
            ProductCategoryDTO dto = ProductCategoryDTO.builder()
                    .id(productCategory.getId())
                    .name(productCategory.getName())
                    .build();
            System.out.println("dto = " + dto);
            productCategoryDTOs.add(dto);
        }

        MyResponse<List<ProductCategoryDTO>> body = MyResponse.<List<ProductCategoryDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(productCategoryDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
    }
}

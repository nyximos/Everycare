package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.ImageDTO;
import wd.team4.everycare.dto.MultipartFileDTO;
import wd.team4.everycare.dto.product.MemberProductListViewDTO;
import wd.team4.everycare.dto.product.ProductFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<MemberProductListViewDTO> webFindAll(Store store);

    ResponseEntity<MyResponse> save(@AuthenticationPrincipal PrincipalDetails principalDetails, ProductFormDTO productFormDTO) throws IOException;

    ResponseEntity<MyResponse> findAll();

    ResponseEntity<MyResponse> findById(Long id);

    ResponseEntity<MyResponse> findAll(Store store);

    ResponseEntity<MyResponse> productFindById(Long id);

    ResponseEntity<MyResponse> updateProduct(Long id, ProductFormDTO productFormDTO) throws IOException;

    ResponseEntity<MyResponse> deleteProduct(Long id);

    ResponseEntity<MyResponse> saveImage(Long id, MultipartFileDTO imageDTO) throws IOException;

    ResponseEntity<MyResponse> removeImage(Long id);

    ResponseEntity<MyResponse> findAllByName(String name);

}

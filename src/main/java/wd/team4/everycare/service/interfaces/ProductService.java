package wd.team4.everycare.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.product.MemberProductsViewDTO;
import wd.team4.everycare.dto.product.ProductFormDTO;
import wd.team4.everycare.dto.response.MyResponse;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<MemberProductsViewDTO> webFindAll(Store store);

    ResponseEntity<MyResponse> save(@AuthenticationPrincipal PrincipalDetails principalDetails, ProductFormDTO productFormDTO) throws IOException;
}
package wd.team4.everycare.service.interfaces;

import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.product.MemberProductsViewDTO;

import java.util.List;

public interface ProductService {
    List<MemberProductsViewDTO> webFindAll(Store store);
}

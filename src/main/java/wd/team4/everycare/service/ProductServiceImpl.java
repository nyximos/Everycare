package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.Product;
import wd.team4.everycare.domain.Store;
import wd.team4.everycare.dto.product.MemberProductsViewDTO;
import wd.team4.everycare.repository.ProductRepository;
import wd.team4.everycare.service.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<MemberProductsViewDTO> webFindAll(Store store) {

        List<Product> products = productRepository.findAllByStore(store);
        List<MemberProductsViewDTO> MemberProductsViewDTOs = new ArrayList<>();

        if(products.isEmpty()){
            return null;
        }

        products.stream().map(product -> product.toMemberProductsViewDTO()).forEach(MemberProductsViewDTOs::add);

        return MemberProductsViewDTOs;
    }
}

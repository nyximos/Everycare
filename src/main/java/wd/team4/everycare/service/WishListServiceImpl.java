package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Product;
import wd.team4.everycare.domain.WishList;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.ProductRepository;
import wd.team4.everycare.repository.WishListRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WishListServiceImpl {

    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;

    public ResponseEntity<MyResponse> save(Long id, PrincipalDetails principalDetails) {

        Member user = principalDetails.getUser();

        Optional<Product> product = productRepository.findById(id);
        Product productEntity = product.orElse(null);

        WishList wish = WishList.builder()
                .member(user)
                .product(productEntity)
                .build();

        wishListRepository.save(wish);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}

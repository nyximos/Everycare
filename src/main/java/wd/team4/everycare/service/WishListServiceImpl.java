package wd.team4.everycare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.config.auth.PrincipalDetails;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Product;
import wd.team4.everycare.domain.WishList;
import wd.team4.everycare.dto.product.ProductListViewDTO;
import wd.team4.everycare.dto.product.WishListDTO;
import wd.team4.everycare.dto.response.MyResponse;
import wd.team4.everycare.dto.response.StatusEnum;
import wd.team4.everycare.repository.ProductRepository;
import wd.team4.everycare.repository.WishListRepository;

import java.util.ArrayList;
import java.util.List;
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

        Optional<WishList> wishList = wishListRepository.findByMemberAndProduct(user, productEntity);

        if (wishList.isEmpty()) {
            WishList wish = WishList.builder()
                    .member(user)
                    .product(productEntity)
                    .build();

            wishListRepository.save(wish);

            System.out.println("wish = " + wish);
            MyResponse body = MyResponse.builder()
                    .header(StatusEnum.OK)
                    .message("성공했슴다~")
                    .build();
            return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
        }


        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공했슴다~")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }

    public ResponseEntity<MyResponse> getAll(PrincipalDetails principalDetails) {

        Member user = principalDetails.getUser();

        List<WishList> wishList = wishListRepository.findAllByMember(user);

        List<WishListDTO> wishListDTOs = new ArrayList<>();

        for (WishList w : wishList) {
            Optional<Product> product = productRepository.findById(w.getProduct().getId());
            Product productEntity = product.orElse(null);

            WishListDTO dto = WishListDTO.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .price(productEntity.getPrice())
                    .storeFileName(productEntity.getStoreFileName())
                    .storeName(productEntity.getStore().getName())
                    .build();

            wishListDTOs.add(dto);
        }

        MyResponse<List<WishListDTO>> body = MyResponse.<List<WishListDTO>>builder()
                .header(StatusEnum.OK)
                .message("성공")
                .body(wishListDTOs)
                .build();

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);


    }

    public ResponseEntity<MyResponse> remove(Long id) {

        wishListRepository.deleteById(id);

        MyResponse body = MyResponse.builder()
                .header(StatusEnum.OK)
                .message("성공")
                .build();
        return new ResponseEntity<MyResponse>(body, HttpStatus.OK);
    }
}

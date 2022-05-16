package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.*;
import wd.team4.everycare.repository.ProductCategoryRepository;
import wd.team4.everycare.repository.StoreRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class InitProductService {

    private final EntityManager em;
    private final StoreRepository storeRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public void dbInit() {
        Optional<Store> store1 = storeRepository.findById((long) 1);
        Optional<ProductCategory> productCategory7 = productCategoryRepository.findById(7L);
        Optional<ProductCategory> productCategory8 = productCategoryRepository.findById(8L);

        Product product1 = createProduct("Max311D 알루미늄휠체어(뒷바퀴분리형)", 387000,
                100,"product1.png", "product1.png",
                "- 뒷바퀴 분리형으로 부피및 무게 취소화로 차량 트렁크 탑재가 용이하며, 알루미늄 차체로 제작되어 무게가 가볍고 등받이가 꺽여지므로 부피를 줄일 수 있어 승용차 탑재시 편리한 제품\n - 차체: 알루미늄\n - 팔받이, 팔걸이 고정식\n- 등받이 꺽기형\n- 뒷바퀴 원터치 착탈 분리형\n - 당김 방식 토글 브레이크\n- 일반 환자 및 장애인이 실내 및 실외 사용 가능",
                1, LocalDateTime.now(), store1.get(), productCategory7.get());
        Product product2 = createProduct("[보장구지원] 케어라인 전동휠체어 나래 200", 2090000,
                100, "product2.png", "product2.png","인체공학적 시트의 고급형모델 나래 200입니다.",
                1, LocalDateTime.now(), store1.get(), productCategory8.get());
        createProductImage("product1.png", "product1.png", product1);
        createProductImage("product2.png", "product2.png", product2);
    }

    private Product createProduct(String name, int price, int inventoryQuantity,
                                  String uploadFileName,String storeFileName,
                                  String comment, int isSale, LocalDateTime createdAt,
                                  Store store, ProductCategory productCategory) {

        Product product = Product.builder()
                .name(name)
                .price(price)
                .inventoryQuantity(inventoryQuantity)
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .comment(comment)
                .isSale(isSale)
                .createdAt(createdAt)
                .store(store)
                .productCategory(productCategory)
                .build();
        em.persist(product);
        return product;
    }

    private ProductImage createProductImage(String uploadFileName, String storeFileName, Product product) {

        ProductImage productImage = ProductImage.builder()
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .product(product)
                .build();
        em.persist(productImage);
        return productImage;
    }
}

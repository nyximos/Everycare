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
        Optional<ProductCategory> productCategory9 = productCategoryRepository.findById(9L);
        Optional<ProductCategory> productCategory10 = productCategoryRepository.findById(10L);

        // 7 혈압계

        Product product1 = createProduct("메델 자동전자 팔뚝형 혈압계 디스플레이탑", 80000,
                50,"product1.png", "product1.png",
                "메델 자동전자 팔뚝형 혈압계 디스플레이탑",
                1, LocalDateTime.now(), store1.get(), productCategory7.get());
        createProductImage("product1.jpg", "product1.jpg", product1);

        Product product2 = createProduct("AND 전자혈압계 UA-704", 60000,
                50,"product1.png", "product1.png",
                "AND 전자혈압계 UA-704",
                1, LocalDateTime.now(), store1.get(), productCategory7.get());
        createProductImage("product2.jpg", "product2.jpg", product2);

        Product product3 = createProduct("오므론 디지털 손목형 자동혈압계 HEM-6051", 126000,
                50,"product3.jpg", "product3.jpg",
                "오므론 디지털 손목형 자동혈압계 HEM-6051",
                1, LocalDateTime.now(), store1.get(), productCategory7.get());
        createProductImage("product3.jpg", "product3.jpg", product3);


        // 8 혈당측정기
        Product product4 = createProduct("케어센스2 플러스 혈당측정기", 15000,
                50,"product4.jpg", "product4.jpg",
                "자기검사용으로 혈중 글루코스를 개인이 스스로 측정하는 시스템으로 측정기 및 검사지를 포함한다.",
                1, LocalDateTime.now(), store1.get(), productCategory8.get());
        createProductImage("product4-1.jpg", "product4-1.jpg", product4);

        Product product5 = createProduct("케어센스/케어센스2 혈당시험지 2박스(100매)", 29800,
                50,"product5.jpg", "product5.jpg",
                "케어센스/케어센스2 혈당시험지 2박스(100매)",
                1, LocalDateTime.now(), store1.get(), productCategory8.get());
        createProductImage("product5-1.jpg", "product5-1.jpg", product5);

        Product product6 = createProduct("케어센스2 플러스 혈당측정기", 48000,
                50,"product6.jpg", "product6.jpg",
                "케어센스2+시험지1박스(50매)+채혈침1박스(200개)+알콜솜1박스(100매)",
                1, LocalDateTime.now(), store1.get(), productCategory8.get());
        createProductImage("product6-1.jpg", "product6-1.jpg", product6);

        Product product7 = createProduct("아큐첵액티브 혈당측정기(기계+채혈기+혈당지10매+채혈침10개)", 48000,
                50,"product7.jpg", "product7.jpg",
                "아큐첵액티브 혈당측정기(기계+채혈기+혈당지10매+채혈침10개)",
                1, LocalDateTime.now(), store1.get(), productCategory8.get());
        createProductImage("product7-1.jpg", "product7-1.jpg", product7);

        Product product8 = createProduct("아큐첵 전용채혈침 소프트클릭스 1박스(200개)", 19800,
                50,"product8.jpg", "product8.jpg",
                "아큐첵액티브 혈당측정기(기계+채혈기+혈당지10매+채혈침10개)",
                1, LocalDateTime.now(), store1.get(), productCategory8.get());
        createProductImage("product8-1.jpg", "product8-1.jpg", product8);

        Product product9 = createProduct("수퍼 글루코닥터 AGM-2200", 55000,
                50,"product9.jpg", "product9.jpg",
                "수퍼 글루코닥터 AGM-2200",
                1, LocalDateTime.now(), store1.get(), productCategory8.get());
        createProductImage("product9.jpg", "product9.jpg", product9);

        Product product10 = createProduct("글루코카드01 혈당시험지2박스(100매)", 49000,
                50,"product10.jpg", "product10.jpg",
                "글루코카드01 혈당시험지2박스(100매)",
                1, LocalDateTime.now(), store1.get(), productCategory8.get());
        createProductImage("product10-1.jpg", "product10-1.jpg", product10);


        // 9 체중
        Product product11 = createProduct("글비접촉식 적외선 온도계 온도측정기 IR-FM", 34800,
                50,"product11.jpg", "product11.jpg",
                "글비접촉식 적외선 온도계 온도측정기 IR-FM",
                1, LocalDateTime.now(), store1.get(), productCategory9.get());
        createProductImage("product11-1.jpg", "product11-1.jpg", product11);
        createProductImage("product11-2.jpg", "product11-2.jpg", product11);
        createProductImage("product11-3.jpg", "product11-3.jpg", product11);
        createProductImage("product11-4.jpg", "product11-4.jpg", product11);
        createProductImage("product11-5.jpg", "product11-5.jpg", product11);


        Product product12 = createProduct("카스 체지방계 BFA-6", 39800,
                50,"product12.jpg", "product12.jpg",
                "카스 체지방계 BFA-6",
                1, LocalDateTime.now(), store1.get(), productCategory9.get());
        createProductImage("product12-1.jpg", "product12-1.jpg", product12);
        createProductImage("product12-2.jpg", "product12-2.jpg", product12);
        createProductImage("product12-3.jpg", "product12-3.jpg", product12);
        createProductImage("product12-4.jpg", "product12-4.jpg", product12);
        createProductImage("product12-5.jpg", "product12-5.jpg", product12);
        createProductImage("product12-6.jpg", "product12-6.jpg", product12);
        createProductImage("product12-7.jpg", "product12-7.jpg", product12);

        Product product13 = createProduct("디웰 체지방 체중계 DW-BS 1658(화이트)/1659(블랙)", 29800,
                50,"product13.jpg", "product13.jpg",
                "디웰 체지방 체중계 DW-BS 1658(화이트)/1659(블랙)",
                1, LocalDateTime.now(), store1.get(), productCategory9.get());
        createProductImage("product13-1.jpg", "product13-1.jpg", product13);
        createProductImage("product13-2.jpg", "product13-2.jpg", product13);
        createProductImage("product13-3.jpg", "product13-3.jpg", product13);
        createProductImage("product13-4.jpg", "product13-4.jpg", product13);
        createProductImage("product13-5.jpg", "product13-5.jpg", product13);
        createProductImage("product13-6.jpg", "product13-6.jpg", product13);

        Product product14 = createProduct("닥터웰 디지털 체중계 DWH-DS1(화이트실버) / DWH-DS2(메탈블랙)", 25800,
                50,"product14.jpg", "product14.jpg",
                "닥터웰 디지털 체중계",
                1, LocalDateTime.now(), store1.get(), productCategory9.get());
        createProductImage("product14.jpg", "product14.jpg", product14);


        //10 보행 보조자
        Product product15 = createProduct("케어맥스 성인용보행기 롤레이터 CA8860L", 132000,
                50,"product16.jpg", "product16.jpg",
                "라온아띠 성인용보행기 KCS-317",
                1, LocalDateTime.now(), store1.get(), productCategory10.get());
        createProductImage("product15-1.jpg", "product15-1.jpg", product15);
        createProductImage("product15-2.jpg", "product15-2.jpg", product15);
        createProductImage("product15-3.jpg", "product15-3.jpg", product15);
        createProductImage("product15-4.jpg", "product15-4.jpg", product15);
        createProductImage("product15-5.jpg", "product15-5.jpg", product15);
        createProductImage("product15-6.jpg", "product15-6.jpg", product15);

        Product product16 = createProduct("케어맥스 성인용보행기 롤레이터 CA8860L", 132000,
                50,"product16.jpg", "product16.jpg",
                "케어맥스 성인용보행기 롤레이터 CA8860L",
                1, LocalDateTime.now(), store1.get(), productCategory10.get());
        createProductImage("product16-1.jpg", "product16-1.jpg", product16);
        createProductImage("product16-2.jpg", "product16-2.jpg", product16);
        createProductImage("product16-3.jpg", "product16-3.jpg", product16);
        createProductImage("product16-4.jpg", "product16-4.jpg", product16);
        createProductImage("product16-5.jpg", "product16-5.jpg", product16);
        createProductImage("product16-6.jpg", "product16-6.jpg", product16);

        Product product17 = createProduct("실버백세 보행보조차 DH-509 성인용보행기 실버카", 135000,
                50,"product17.jpg", "product17.jpg",
                "보행보조차 DH-509",
                1, LocalDateTime.now(), store1.get(), productCategory10.get());
        createProductImage("product17-1.jpg", "product17-1.jpg", product17);
        createProductImage("product17-2.jpg", "product17-2.jpg", product17);
        createProductImage("product17-3.jpg", "product17-3.jpg", product17);
        createProductImage("product17-4.jpg", "product17-4.jpg", product17);
        createProductImage("product17-5.jpg", "product17-5.jpg", product17);

        Product product18 = createProduct("성인용보행기 실버카 트라사이드 TRA-SIDE", 165000,
                50,"product18.png", "product18.png",
                "성인용보행기 실버카 트라사이드 TRA-SIDE",
                1, LocalDateTime.now(), store1.get(), productCategory10.get());
        createProductImage("product18-1.png", "product18-1.png", product18);

        Product product19 = createProduct("라온아띠 성인용보행기 KCS-315", 166000,
                50,"product19.jpg", "product19.jpg",
                "라온아띠 성인용보행기 KCS-315",
                1, LocalDateTime.now(), store1.get(), productCategory10.get());
        createProductImage("product19-1.jpg", "product19-1.jpg", product19);
        createProductImage("product19-2.jpg", "product19-2.jpg", product19);
        createProductImage("product19-3.jpg", "product19-3.jpg", product19);
        createProductImage("product19-4.jpg", "product19-4.jpg", product19);
        createProductImage("product19-5.jpg", "product19-5.jpg", product19);

        Product product20 = createProduct("성인용보행기 롤레이터 실버카 BH-90", 218000,
                50,"product20.jpg", "product20.jpg",
                "성인용보행기 롤레이터 실버카 BH-90",
                1, LocalDateTime.now(), store1.get(), productCategory10.get());
        createProductImage("product20-1.jpg", "product20-1.jpg", product20);

//        Product product21 = createProduct("시마 하이토모카/듀오ST/포르테/샤니워커SP", 218000,
//                50,"product20.jpg", "product20.jpg",
//                "성인용보행기 롤레이터 실버카 BH-90",
//                1, LocalDateTime.now(), store1.get(), productCategory10.get());
//        createProductImage("product20-1.jpg", "product20-1.jpg", product20);


        Product product100 = createProduct("Max311D 알루미늄휠체어(뒷바퀴분리형)", 387000,
                100,"product1.png", "product1.png",
                "- 뒷바퀴 분리형으로 부피및 무게 취소화로 차량 트렁크 탑재가 용이하며, 알루미늄 차체로 제작되어 무게가 가볍고 등받이가 꺽여지므로 부피를 줄일 수 있어 승용차 탑재시 편리한 제품\n - 차체: 알루미늄\n - 팔받이, 팔걸이 고정식\n- 등받이 꺽기형\n- 뒷바퀴 원터치 착탈 분리형\n - 당김 방식 토글 브레이크\n- 일반 환자 및 장애인이 실내 및 실외 사용 가능",
                1, LocalDateTime.now(), store1.get(), productCategory7.get());
        Product product101 = createProduct("[보장구지원] 케어라인 전동휠체어 나래 200", 2090000,
                100, "product2.png", "product2.png","인체공학적 시트의 고급형모델 나래 200입니다.",
                1, LocalDateTime.now(), store1.get(), productCategory8.get());
        createProductImage("product1.png", "product1.png", product100);
        createProductImage("product2.png", "product2.png", product101);
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

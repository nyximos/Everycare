package wd.team4.everycare.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wd.team4.everycare.domain.ProductCategory;

import javax.persistence.EntityManager;

@Component
@Transactional
@RequiredArgsConstructor
public class InitProductCategoryService {

    private final EntityManager em;

    public void dbInit(){

        ProductCategory productCategory1 = createParentProductCategory("건강측정");
        ProductCategory productCategory2 = createParentProductCategory("보행보조");
        ProductCategory productCategory3 = createParentProductCategory("배변간병");
        ProductCategory productCategory4 = createParentProductCategory("목욕보조");
        ProductCategory productCategory5 = createParentProductCategory("생활건강");
        ProductCategory productCategory6 = createParentProductCategory("생활편의");


        createChildProductCategory("건강측정", productCategory1);
        createChildProductCategory("혈당측정기/혈당지", productCategory1);
        createChildProductCategory("체중/체지방/체온계", productCategory1);

        createChildProductCategory("보행보조차", productCategory2);
        createChildProductCategory("지팡이", productCategory2);
        createChildProductCategory("워커/보행차", productCategory2);
        createChildProductCategory("휠체어", productCategory2);  //삭제
        createChildProductCategory("전동휠체어/스쿠터", productCategory2); //삭제

        createChildProductCategory("성인용기저귀/패드", productCategory3);
        createChildProductCategory("요실금팬티", productCategory3);
        createChildProductCategory("소변기/좌변기", productCategory3);
        createChildProductCategory("욕창예방매트/방석", productCategory3); //삭제
        createChildProductCategory("간병침대/간병용품", productCategory3); //삭제
        createChildProductCategory("환자영양식", productCategory3); //삭제

        createChildProductCategory("목욕의자", productCategory4);
        createChildProductCategory("성인용기저귀/패드", productCategory4);
        createChildProductCategory("욕실용품", productCategory4);

        createChildProductCategory("기능성신발/간호화", productCategory5);
        createChildProductCategory("식품/건강식품", productCategory5);
        createChildProductCategory("틀니관련용품", productCategory5);
        createChildProductCategory("침구/반신욕/찜질기", productCategory5); //삭제
        createChildProductCategory("안마/재활/물리치료", productCategory5); //삭제
        createChildProductCategory("호흡/신종플루용품", productCategory5); //삭제

        createChildProductCategory("효도전화기/증폭기", productCategory6);
        createChildProductCategory("돋보기", productCategory6);
        createChildProductCategory("주방생활용품", productCategory6);
        createChildProductCategory("생활가전/가구", productCategory6); //삭제

    }

    private ProductCategory createParentProductCategory(String name){
        ProductCategory productCategory = ProductCategory.builder()
                .name(name)
                .build();
        em.persist(productCategory);
        return productCategory;
    }

    private ProductCategory createChildProductCategory(String name, ProductCategory parent) {
        ProductCategory productCategory = ProductCategory.builder()
                .name(name)
                .productCategory(parent)
                .build();
        em.persist(productCategory);
        return productCategory;
    }

}

package wd.team4.everycare.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.Product;
import wd.team4.everycare.domain.QWishList;

import java.util.List;

import static wd.team4.everycare.domain.QProduct.product;
import static wd.team4.everycare.domain.QWishList.*;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Product> findAllByName(String name) {
        return queryFactory
                .selectFrom(product)
                .where(product.name.contains(name),
                        product.isSale.eq(1))
                .fetch();
    }

    public List<Product> findAllByCategory(String categoryName){
        return queryFactory
                .selectFrom(product)
                .where(product.productCategory.name.eq(categoryName))
                .fetch();
    }

    public List<Product> findBestProducts(){
        return queryFactory
                .select(product)
                .from(wishList)
                .join(wishList.product, product)
                .groupBy(wishList.product)
                .orderBy(wishList.count().desc())
                .limit(3)
                .fetch();
    }
}

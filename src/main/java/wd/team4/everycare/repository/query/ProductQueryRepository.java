package wd.team4.everycare.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.Product;

import java.time.LocalDateTime;
import java.util.List;

import static wd.team4.everycare.domain.QProduct.product;

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

    /* TODO 총 매출 통계 */
    public List<Product> getStatistics(LocalDateTime 시작, LocalDateTime 종료){
        return queryFactory
                .select(product)
                .fetch();
    }
}

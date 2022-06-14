package wd.team4.everycare.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.OrderProduct;
import wd.team4.everycare.domain.Store;

import java.time.LocalDateTime;
import java.util.List;

import static wd.team4.everycare.domain.QOrderProduct.orderProduct;

@Repository
@RequiredArgsConstructor
public class OrderProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<OrderProduct> findStatistics(LocalDateTime startDate, LocalDateTime endDate, Store store){
        return queryFactory
                .selectFrom(orderProduct)
                .where(orderProduct.product.store.eq(store),
                        orderProduct.order.paymentTime.between(startDate, endDate))
                .fetch();
    }
}

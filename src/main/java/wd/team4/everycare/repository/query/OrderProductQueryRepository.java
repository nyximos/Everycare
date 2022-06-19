package wd.team4.everycare.repository.query;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.Store;

import java.time.LocalDateTime;
import java.util.List;

import static wd.team4.everycare.domain.QOrder.order;
import static wd.team4.everycare.domain.QOrderProduct.orderProduct;

@Repository
@RequiredArgsConstructor
public class OrderProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Tuple> findStatistics(LocalDateTime startDate, LocalDateTime endDate, Store store){
        return queryFactory
                .select(orderProduct.amount, orderProduct.order.paymentTime)
                .from(orderProduct)
                .join(orderProduct.order, order)
                .where(orderProduct.product.store.eq(store),
                        orderProduct.order.paymentTime.between(startDate, endDate))
                .groupBy(orderProduct.order.paymentTime, orderProduct.id)
                .fetch();
    }
}

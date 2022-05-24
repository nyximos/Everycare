package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Order;
import wd.team4.everycare.domain.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberAndStatus(Member member, OrderStatus orderStatus);
}

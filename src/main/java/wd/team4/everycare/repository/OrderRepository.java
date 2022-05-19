package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

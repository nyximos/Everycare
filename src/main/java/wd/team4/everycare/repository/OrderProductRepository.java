package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.OrderProduct;
import wd.team4.everycare.domain.Product;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @EntityGraph(attributePaths = {"order"})
    List<OrderProduct> findByOrderId(Long orderId);

    @EntityGraph(attributePaths = {"order"})
    Optional<OrderProduct> findByOrderIdAndProduct(Long orderId, Product product);
}

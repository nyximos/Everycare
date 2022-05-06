package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.ProductImage;

import java.util.List;

public interface ProducImageRepository extends JpaRepository<ProductImage, Long> {

    @EntityGraph(attributePaths = {"product"})
    List<ProductImage> findAllByProductId(Long id);
}

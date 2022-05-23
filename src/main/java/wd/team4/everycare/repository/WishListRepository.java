package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Product;
import wd.team4.everycare.domain.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findAllByMember(Member user);

    Optional<WishList> findByMemberAndProduct(Member member, Product product);

    @EntityGraph(attributePaths = {"product"})
    void deleteByProductId(Long id);
}

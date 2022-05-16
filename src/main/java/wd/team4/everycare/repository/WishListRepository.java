package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {
}

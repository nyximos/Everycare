package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}

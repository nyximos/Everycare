package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.Store;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findAllByAdminApproval(int adminApproval);

    List<Store> findByMember(Member member);

}

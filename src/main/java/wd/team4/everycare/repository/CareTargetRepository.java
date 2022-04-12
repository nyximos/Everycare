package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.CareTarget;

import java.util.List;

@Repository
public interface CareTargetRepository extends JpaRepository<CareTarget, Long> {
    List<CareTarget> findAllByMember_Id(String memberId);
}

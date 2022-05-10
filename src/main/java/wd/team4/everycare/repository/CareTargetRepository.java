package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareTargetRepository extends JpaRepository<CareTarget, Long> {
  
    @EntityGraph(attributePaths = {"member"})
    List<CareTarget> findAllByMemberId(String memberId);

    List<CareTarget> findAllByMember(Member user);

}

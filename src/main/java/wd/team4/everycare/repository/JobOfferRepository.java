package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.JobOffer;
import wd.team4.everycare.domain.Member;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

    List<JobOffer> findByMember(Member member);
}

package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.ActivityClassification;
import wd.team4.everycare.domain.Bedge;

import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Bedge, Long> {
    Optional<Bedge> findByActivityClassification(ActivityClassification activityClassification);
}

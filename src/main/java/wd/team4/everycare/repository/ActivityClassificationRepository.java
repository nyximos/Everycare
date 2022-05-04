package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.ActivityClassification;

public interface ActivityClassificationRepository extends JpaRepository<ActivityClassification, Long> {
}

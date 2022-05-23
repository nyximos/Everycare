package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.HealthClassification;

public interface HealthClassificationRepository extends JpaRepository<HealthClassification, Long> {
}
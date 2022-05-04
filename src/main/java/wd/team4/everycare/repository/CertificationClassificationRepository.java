package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.CertificationClassification;

public interface CertificationClassificationRepository extends JpaRepository<CertificationClassification, Long> {
    CertificationClassification findByName(String classificationName);
}

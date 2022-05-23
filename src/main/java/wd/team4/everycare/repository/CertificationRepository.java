package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Certification;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    List<Certification> findAllByCareSitterId(Long id);

    @EntityGraph(attributePaths = {"careSitter"})
    List<Certification> findAllByAdminApproval(int i);

    List<Certification> findAllByCareSitter(CareSitter careSitter);
}

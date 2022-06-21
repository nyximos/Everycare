package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.CareTarget;
import wd.team4.everycare.domain.CareTargetImage;

import java.util.List;

@Repository
public interface CareTargetImageRepository extends JpaRepository<CareTargetImage, Long> {

    List<CareTargetImage> findAllByCareTarget(CareTarget careTarget);

    @EntityGraph(attributePaths = {"careTarget"})
    List<CareTargetImage> findAllByCareTargetId(Long id);

}

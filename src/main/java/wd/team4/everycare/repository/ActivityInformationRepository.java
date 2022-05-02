package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.ActivityInformation;

import java.util.List;

public interface ActivityInformationRepository extends JpaRepository<ActivityInformation, Long> {
    List<ActivityInformation> findAllByCareTargetScheduleId(Long id);
}

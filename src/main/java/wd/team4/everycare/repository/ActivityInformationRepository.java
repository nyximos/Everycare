package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wd.team4.everycare.domain.ActivityInformation;

import java.util.List;

public interface ActivityInformationRepository extends JpaRepository<ActivityInformation, Long> {

    @EntityGraph(attributePaths = {"activityClassification"})
    List<ActivityInformation> findAllByCareTargetScheduleId(Long id);

    @Query("select a from ActivityInformation a left join a.careTargetSchedule c where c.id = :id")
    List<ActivityInformation> findAllCareTargetSchedule(Long id);
}

package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Member;
import wd.team4.everycare.domain.MemberSchedule;

import java.util.List;

public interface MemberScheduleRepository extends JpaRepository<MemberSchedule, Long> {

    List<MemberSchedule> findAllByMember(Member user);

}
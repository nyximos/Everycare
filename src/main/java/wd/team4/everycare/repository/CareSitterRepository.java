package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Member;

import java.util.List;
import java.util.Optional;

public interface CareSitterRepository extends JpaRepository<CareSitter, Long>{
    List<CareSitter> findAllByDisclosureStatus(int disclosure);

    CareSitter findByMember(Member member);
}

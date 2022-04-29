package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.CareSitter;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareSitterRepository extends JpaRepository<CareSitter, Long>{
    Optional<CareSitter> findByMember_Id(String memberId);
    List<CareSitter> findAllByDisclosureStatus(int disclosure);
}

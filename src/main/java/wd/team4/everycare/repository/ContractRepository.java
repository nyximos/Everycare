package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Contract;
import wd.team4.everycare.domain.JobOffer;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
  
    List<Contract> findByStatusAndJobOffer(int status, JobOffer jobOffer);

    List<Contract> findByCareSitterAndStatus(CareSitter careSitter, int status);
}

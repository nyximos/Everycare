package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Contract;
import wd.team4.everycare.domain.JobOffer;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByContractStatusAndJobOffer(int status, JobOffer jobOffer);
}

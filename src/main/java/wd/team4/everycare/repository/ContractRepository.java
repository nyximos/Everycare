package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}

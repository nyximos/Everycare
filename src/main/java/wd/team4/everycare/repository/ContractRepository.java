package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.CareSitter;
import wd.team4.everycare.domain.Contract;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByCareSitterAndStatus(CareSitter careSitter, int status);
}

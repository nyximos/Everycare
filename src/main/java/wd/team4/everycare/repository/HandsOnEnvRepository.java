package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.entity.HandsOnEnv;

@Repository
public interface HandsOnEnvRepository extends JpaRepository<HandsOnEnv, Integer> {
    public HandsOnEnv findByUsernameAndEmail(String username, String email);
}
package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.entity.UserDAO;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Integer> {

    UserDAO findByUsername(String username);

    long countByUsername(String username);
}

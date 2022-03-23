package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.entity.RefreshTokenDAO;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenDAO, String> {
    Optional<RefreshTokenDAO> findByUsernameAndRefreshToken(String username, String refreshToken);
}
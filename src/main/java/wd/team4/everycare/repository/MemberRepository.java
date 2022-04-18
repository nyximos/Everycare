package wd.team4.everycare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wd.team4.everycare.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}

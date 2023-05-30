package com.toyboard.jeongmin.member.repository;

import com.toyboard.jeongmin.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailValueAndPasswordValue(String email, String password);

    boolean existsByEmailValue(String email);

    Member findByEmailValue(String email);
}

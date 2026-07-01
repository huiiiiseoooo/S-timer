package com.choishingong.s_timer.domain.user.repository;

import com.choishingong.s_timer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// extend jpa 이게 DB 기능을 자동으로 만들어줌
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email); // 이메일 중복 확인

    Optional<User> findByEmail(String email); // 이메일로 사용자 찾음
}

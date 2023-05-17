package com.kb.jjan.domain.user.repository;

import com.kb.jjan.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByFamCode(String famcode);
}

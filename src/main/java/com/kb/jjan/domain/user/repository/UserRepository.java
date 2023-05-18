package com.kb.jjan.domain.user.repository;

import com.kb.jjan.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByFamCode(String famCode);

    @Query("select u from User u where u.email=:email AND u.password=:password") //반드시 table alias 사용해야한다.
    <Optional> User login(@Param("email") String email, @Param("password") String password);

    boolean existsByEmail(String email);

    @Query("select u from User u where u.famCode=:famCode")
    List<User> findByFamCode(@Param("famCode") String famCode);

}

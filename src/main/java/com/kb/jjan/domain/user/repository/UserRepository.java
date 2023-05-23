package com.kb.jjan.domain.user.repository;

import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByFamCode(String famCode);

    @Query("select u from User u where u.email=:email AND u.password=:password") //반드시 table alias 사용해야한다.
    <Optional> User login(@Param("email") String email, @Param("password") String password);

    boolean existsByEmail(String email);

    @Query("select u from User u where u.famCode=:famCode AND u.userId!=:userId")
    List<User> findByFamCode(@Param("famCode") String famCode, @Param("userId") long userId);

    @Modifying()
    @Query("UPDATE User u SET u.phoneNum = :phoneNum WHERE u.userId = :userId")
    int updatePhoneNum(@Param("phoneNum") String phoneNum, @Param("userId") long userId);

    @Modifying()
    @Query("UPDATE User u SET u.cheerUpMsg = :msg WHERE u.userId = :userId")
    void updateCheerUpMsg(@Param("userId") long userId, @Param("msg") String msg);

}

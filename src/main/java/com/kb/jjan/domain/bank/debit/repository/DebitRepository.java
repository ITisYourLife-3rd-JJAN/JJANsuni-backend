package com.kb.jjan.domain.bank.debit.repository;

import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.dto.DebitRequest;
import com.kb.jjan.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DebitRepository extends JpaRepository<Debit, Long> {
    @Query("SELECT d FROM Debit d WHERE d.sendUser = :userId OR d.receivedUser = :userId")
    List<Debit> findByUserId(Long userId);
}

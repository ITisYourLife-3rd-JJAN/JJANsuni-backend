package com.kb.jjan.domain.bank.debit.repository;

import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.dto.DebitRequest;
import com.kb.jjan.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DebitRepository extends JpaRepository<Debit, Long> {
    List<Debit> findBySendUserUserIdOrReceivedUserUserId(long userId1, long userId2);
}

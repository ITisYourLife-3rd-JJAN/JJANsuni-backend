package com.kb.jjan.domain.bank.debit.repository;

import com.kb.jjan.domain.bank.debit.Debit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DebitRepository extends JpaRepository<Debit, Long> {
List<Debit> findBySendUserUserIdOrReceivedUserUserId(long userId, long userId2);
}

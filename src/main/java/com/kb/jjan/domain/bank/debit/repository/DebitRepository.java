package com.kb.jjan.domain.bank.debit.repository;

import com.kb.jjan.domain.bank.debit.Debit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitRepository extends JpaRepository<Debit, Long> {
}
